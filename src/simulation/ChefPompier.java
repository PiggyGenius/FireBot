package simulation;

import chemin.*;
import enumerations.*;
import simulation.*;
import simulation.evenement.*;
import robot.*;
import enumerations.NatureTerrain;

import java.util.*;

public class ChefPompier {

	private Simulateur sim;

	private List<Incendie> listeIncendie;

	/** Constructeur de ChefPompier
	 * @param sim Le simulateur */
	public ChefPompier(Simulateur sim) {
		this.sim = sim;
		this.listeIncendie = sim.getSimulation().getListeIncendie();
	}

	/** @return le simulateur */
	public Simulateur getSimulateur() {
		return this.sim;
	}

	/** Planifie un deplacement d'un robot pour aller eteindre un feu */
	public void calculDeplacementExtinction() {
		if (listeIncendie.isEmpty()) {
			return;
		}

		for (Iterator<Incendie> it = this.listeIncendie.iterator(); it.hasNext();) {
			Incendie incendie = it.next();
			if (incendie.getIntensite() <= 0) {
				it.remove();
			} else if (! incendie.getEnExtinction()) {
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
					incendie.setEnExtinction(true);
					best_r.planifierDeplacement(best_c, incendie, this, true);
				}
			}
		}
	}


	/** Planifie un deplacement pour envoyer le robot se remplir
	 * @param r le robot */
	public void calculDeplacementRemplissage(Robot r) {
		Chemin c;
		Chemin best_c = new Chemin();
		best_c.setTemps(Double.MAX_VALUE);

		for (Iterator<Case> it = this.sim.getSimulation().getListeEau().iterator(); it.hasNext();) {
			Case case_cour = it.next();
			for (Case voisin : this.getVoisins(case_cour, r.getDistanceRemplissage())) {
				c = this.getChemin(voisin, r);
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


	/** Planifie la suite d'un deplacement (deversement ou remplissage)
	 * @param r le robot
	 * @param incendie l'incendie (null si remplissage)
	 * @param extinction booleen, vrai si deplacement pour une extincion */
	public void finDeplacement(Robot r, Incendie incendie, boolean extinction) {
		if (extinction) {
			this.calculDeversement(r, incendie);	
		} else {
			this.calculRemplissage(r);	
		}	
	}


	/** Planifie les deversements d'un robot sur un incendie
	 * @param r le robot
	 * @param incendie l'incendie */
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
		this.sim.ajouteEvenement(new EvenementDeversementFin(temps, this, r, incendie));
	}


	/** Planifie le remplissage d'un robot
	 * @param r le robot a remplir */
	public void calculRemplissage(Robot r) {
		this.sim.ajouteEvenement(new EvenementRemplissage(this.sim.getDateSimulation(), this, r));
		this.sim.ajouteEvenement(new EvenementRemplissageFin(this.sim.getDateSimulation() + r.getTempsRemplissage(), this, r));
	}



	 /** @param c la case d'eau
	 * @param dist la distance a laquelle le robot doit se trouver de l'eau
	 * @return la liste des cases sur lequel le robot peut etre pour se remplir */
	private List<Case> getVoisins(Case c, int dist) {
		List<Case> res = new ArrayList<Case>();
		if (dist == 0) {
			res.add(c);
		} else if (dist == 1) {
			Carte carte = this.sim.getSimulation().getCarte();
			for (Direction dir : Direction.values()) {
				try {
					res.add(carte.getVoisin(c, dir));
				} catch (IllegalArgumentException e) {
					// OSEF
				}
			}
		} else {
			throw new IllegalArgumentException("Trop loin pour se remplir.");
		}
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
