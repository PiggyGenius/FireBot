package simulation;

import chemin.*;
import simulation.*;
import robot.*;
import enumerations.NatureTerrain;

public class ChefPompier {

	private DonneesSimulation donnees;

	public ChefPompier(DonneesSimulation donnees) {
		this.donnees = donnees;
	}

	/** Teste tous les robots disponibles
	 *  @param incendie Incendie destination
	 **/
	public void choisirRobot(Incendie incendie) {
		Robot best_r = this.donnees.getRobot(0);
		Chemin best_c = this.getChemin(incendie.getCase(), best_r);

		for (int i = 1; i < donnees.getNbRobots(); i++) {
			Robot r = this.donnees.getRobot(i);
			Chemin c = this.getChemin(incendie.getCase(), r);
			if (c != null && c.getTemps() < best_c.getTemps()) {
				best_r = r;
				best_c = c;
			}
		}

		best_r.planifierAction(best_c);
	}


	/**
	 * @param destination Case de destination
	 * @param pompier Robot qui fera le déplacement
	 * @return Chemin pour se rendre à destination avec le temps du trajet
	 **/
	public Chemin getChemin(Case dest, Robot pompier) {
		return this.donnees.getChemin(pompier.getPosition(), dest, pompier.getVitesseMap());
	}
}
