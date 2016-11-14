package simulation.evenement;

import robot.*;
import simulation.*;

public class EvenementRemplissageFin extends Evenement {

	private Robot robot;

	public EvenementRemplissageFin(double date, ChefPompier chef, Robot r) {
		super(date, chef);
		this.robot = r;
	}

	public void execute() {
		System.out.println("Remplissage terminé");
		robot.liberer();
	}
}
