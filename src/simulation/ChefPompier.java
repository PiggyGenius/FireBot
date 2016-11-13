package simulation;

import chemin.*;
import simulation.*;
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
	public void choisirRobot() {
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
				best_r.planifierDeplacement(best_c, this);
				it.remove();
			}
		}
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
