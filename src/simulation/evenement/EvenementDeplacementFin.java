package simulation.evenement;

import robot.*;
import simulation.*;

public class EvenementDeplacementFin extends Evenement {

	private Robot robot;

	/* indique si c'est un deplacement pour une extinction (True)
	 * ou pour un remplissage (False) */
	boolean extinction;

	public EvenementFinDeplacement(double date, Robot r, boolean extinction) {
		super(date);
		this.robot = r;
		this.extinction = extinction;
	}

	public void execute() {
		// TODO
		// Appeler methode du chef pompier, qui
		// calculera le nombre de deversements necessaires
		// et appelera une méthode du Robot, qui
		// planifiera les evenements de deversement + fin
		// de deversement
		return;
	}
}
