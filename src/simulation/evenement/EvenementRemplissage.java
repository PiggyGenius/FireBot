package simulation.evenement;

import robot.*;
import simulation.*;

public class EvenementRemplissage extends Evenement {

	private Robot robot;

	public EvenementRemplissage(double date, Robot r) {
		super(date);
		this.robot = r;
	}

	public void execute() {
		System.out.println("Remplissage"); // DEBUG
		this.robot.remplirReservoir();
	}
}
