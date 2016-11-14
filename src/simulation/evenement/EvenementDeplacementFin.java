package simulation.evenement;

import robot.*;
import simulation.*;

public class EvenementDeplacementFin extends Evenement {

	private Robot robot;

	/* indique si c'est un deplacement pour une extinction (True)
	 * ou pour un remplissage (False) */
	private boolean extinction;

	private Incendie incendie;

	public EvenementDeplacementFin(double date, ChefPompier chef, Robot r, Incendie incendie, boolean extinction) {
		super(date, chef);
		this.robot = r;
		this.extinction = extinction;
		this.incendie = incendie;
	}

	public void execute() {
		System.out.println("Deplacement terminé");
		this.chef.finDeplacement(this.robot, this.incendie, extinction);
	}
}
