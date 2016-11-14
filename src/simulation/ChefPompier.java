package simulation;

import chemin.*;
import simulation.*;
import simulation.evenement.*;
import robot.*;
import enumerations.NatureTerrain;

import java.util.*;

public class ChefPompier {

	private Simulateur sim;

	private List<Incendie> listeIncendie;

	public ChefPompier(Simulateur sim) {
		this.sim = sim;
		this.listeIncendie = new ArrayList<Incendie>();
		for (Incendie i : sim.getSimulation().getListeIncendie()) {
			this.listeIncendie.add(new Incendie(i));
		}
	}

	public Simulateur getSimulateur() {
		return this.sim;
	}

	/** Teste tous les robots disponibles
	 *  @param incendie Incendie destination
	 **/
	public void calculDeplacement() {
		if (listeIncendie.isEmpty()) {
			return;
		}

		for (Iterator<Incendie> it = this.listeIncendie.iterator(); it.hasNext();) {
			Incendie incendie = it.next();
			Robot best_r = null;
			Chemin best_c = new Chemin();
			best_c.setTemps(Double.MAX_VALUE);

			for (int i = 0; i < this.sim.getSimulation().getNbRobots(); i++) {
				Robot r = this.sim.getSimulation().getRobot(i);
				if (r.isDispo()) {
					Chemin c = this.getChemin(incendie.getCase(), r);
					if (c != null && c.getTemps() < best_c.getTemps()) {
						best_r = r;
						best_c = c;
					}
				}
			}

			if (best_r != null) {
				best_r.planifierDeplacement(best_c, incendie, this, true);
				it.remove();
			}
		}
	}


	public void finDeplacement(Robot r, Incendie incendie, boolean extinction) {
		if (extinction) {
			this.calculDeversement(r, incendie);	
		} else {
			this.calculRemplissage(r);	
		}	
	}


	public void calculDeversement(Robot r, Incendie incendie) {
		double temps = this.sim.getDateSimulation();
		int intensite = incendie.getIntensite();
		int capacite = r.getQteReservoir();
		if (r.getCapaciteReservoir() == 0) {
			capacite = Integer.MAX_VALUE;
		}
		int volume = r.getLitresUnitaire();
		int extinctions = 0;
		while (intensite > 0 && capacite >= volume) {
			capacite -= volume;
			intensite -= volume;
			extinctions += 1;
			this.sim.ajouteEvenement(new EvenementDeversement(
					temps, this, r, r.getLitresUnitaire(), incendie));
			temps += r.getTempsUnitaire();
		}
		this.sim.ajouteEvenement(new EvenementDeversementFin(temps, this, r));
	}


	public void calculRemplissage(Robot r) {
		Chemin c;
		Chemin best_c = new Chemin();
		best_c.setTemps(Double.MAX_VALUE);

		for (Iterator<Case> it = this.sim.getSimulation().getListeEau().iterator(); it.hasNext();) {
			Case case_cour = it.next();
			for (Case voisin : this.getVoisins(case_cour, r.getDistanceRemplissage())) {
				c = this.getChemin(case_cour, r);
				if (c != null && c.getTemps() < best_c.getTemps()) {
					best_c = c;
				}
			}
			if (best_c == null) {
				throw new IllegalArgumentException("Pas de point d'eau accessible.");
			}
		}
		r.planifierDeplacement(best_c, null, this, false);
	}

	private List<Case> getVoisins(Case c, int dist) {
		List<Case> res = new ArrayList<Case>();
		// TODO modifier pour retourner les voisins
		res.add(c);
		return res;
	}



	/**
	 * @param dest Case de destination
	 * @param pompier Robot qui fera le déplacement
	 * @return Chemin pour se rendre à destination avec le temps du trajet
	 **/
	public Chemin getChemin(Case dest, Robot pompier) {
		return this.sim.getSimulation().getChemin(pompier.getPosition(), dest, pompier.getVitesseMap());
	}
}
