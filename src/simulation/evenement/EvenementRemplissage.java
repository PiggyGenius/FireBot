package simulation.evenement;

import robot.*;
import simulation.*;

public class EvenementRemplissage extends Evenement {

	private Robot robot;

	public EvenementRemplissage(double date, ChefPompier chef, Robot r) {
		super(date, chef);
		this.robot = r;
	}

	public void execute() {
		this.robot.remplirReservoir();
	}
}
