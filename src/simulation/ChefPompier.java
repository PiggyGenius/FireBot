package simulation;

import chemin.*;
import simulation.*;
import robot.*;
import enumerations.NatureTerrain;

public class ChefPompier {

	private Simulateur sim;

	public ChefPompier(Simulateur sim) {
		this.sim = sim;
	}

	/** Teste tous les robots disponibles
	 *  @param incendie Incendie destination
	 **/
	public void choisirRobot(Incendie incendie) {
		Robot best_r = null;
		Chemin best_c = new Chemin();
		best_c.setTemps(Double.MAX_VALUE);

		for (int i = 0; i < this.sim.getSimulation().getNbRobots(); i++) {
			Robot r = this.sim.getSimulation().getRobot(i);
			Chemin c = this.getChemin(incendie.getCase(), r);
			if (c != null && c.getTemps() < best_c.getTemps()) {
				best_r = r;
				best_c = c;
			}
		}
		
		if (best_r != null) {
			best_r.planifierAction(best_c, this.sim);
		} else {
			throw new IllegalArgumentException("We can't go there, let it burn");
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
