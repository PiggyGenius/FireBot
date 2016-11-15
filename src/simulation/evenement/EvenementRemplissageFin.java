package simulation.evenement;

import robot.*;
import simulation.*;

public class EvenementRemplissageFin extends Evenement {

	private Robot robot;

	/** Constructeur d'evenement de remplissage
	 * @param date la date de l'evenement
	 * @param chef le ChefPompier
	 * @param r le robot */
	public EvenementRemplissageFin(double date, ChefPompier chef, Robot r) {
		super(date, chef);
		this.robot = r;
	}

	/** Execute les actions associees a l'evenement */
	public void execute() {
		// System.out.println("Remplissage terminé");
		robot.liberer();
	}
}
