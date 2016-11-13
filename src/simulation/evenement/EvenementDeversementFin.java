package simulation.evenement;

import robot.*;
import simulation.*;

public class EvenementDeversementFin extends Evenement {

	private Robot robot;

	public EvenementFinDeversement(double date, ChefPompier chef, Robot r) {
		super(date, chef);
		this.robot = r;
	}

	public void execute() {
		// appeler methode du chef pompier, qui va 
		// calculer le plus court chemin à un point d'eau
		// et appeler une méthode du Robot pour ajouter une serie de deplacement
	}
}
