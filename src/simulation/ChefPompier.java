package simulation;

import chemin.*;
import simulation.*;
import robot.*;
import enumerations.NatureTerrain;

public class ChefPompier {
	DonneesSimulation donnees;

	public ChefPompier(DonneesSimulation donnees) {
		this.donnees = donnees;
	}

	/** Teste tous les robots disponibles
	 *  @param incendie Incendie destination
	 *  @return Robot le plus proche de l'incendie
	 **/
	public Robot ChoisirRobot(Incendie incendie) {
		// calculer plus court chemin pour tous les robots
		// choisir le robot le plus proche de l'incendie
		return new RobotRoues(new Case(0,0,NatureTerrain.ROCHE),45.0);
	}

	/**
	 * @param destination Case de destination
	 * @param pompier Robot qui fera le déplacement
	 * @return Liste de Case pour se rendre à destination avec le temps du trajet
	 **/
	public Chemin getChemin(Case destination,Robot pompier){
		return this.donnees.getChemin(new Case(20,45,NatureTerrain.ROCHE),destination,pompier.getVitesseMap());
	}
}
