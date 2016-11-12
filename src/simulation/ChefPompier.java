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

	public Robot ChoisirRobot(Incendie incendie) {
		// calculer plus court chemin pour tous les robots
		// choisir le robot le plus proche de l'incendie
		return new RobotPattes(new Case(0,0,NatureTerrain.ROCHE),45.0);
	}

	/** 
	 * @param destination Case de destination
	 * @return Liste de Case pour se rendre à destination avec le temps du trajet
	 **/
	public Chemin getChemin(Case destination,Robot pompier){
		return this.donnees.getChemin(new Case(0,15,NatureTerrain.ROCHE),destination,pompier.getVitesseMap());
	}
}
