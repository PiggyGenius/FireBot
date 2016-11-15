package simulation.evenement;

import robot.*;
import simulation.*;

public class EvenementDeversementFin extends Evenement {

	private Robot robot;

	private Incendie incendie;

	/** Constructeur d'evenement de fin de deversement
	 * @param date date de l'evenement
	 * @param chef le ChefPompier
	 * @param r le robot
	 * @param incendie l'incendie */
	public EvenementDeversementFin(double date, ChefPompier chef, Robot r, Incendie incendie) {
		super(date, chef);
		this.robot = r;
		this.incendie = incendie;
	}

	/** Execute les actions associees a l'evenement */
	public void execute() {
		// System.out.println("Déversement terminé");
		this.incendie.setEnExtinction(false);
		if (this.robot.getCapaciteReservoir() == 0) {
			this.robot.liberer();
		} else {
			this.chef.calculDeplacementRemplissage(this.robot);
		}
	}
}
