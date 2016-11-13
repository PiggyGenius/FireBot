package simulation.evenement;

import robot.*;
import simulation.*;

public class EvenementDeplacement extends Evenement {

	private Robot robot;

	private Case dest;

	public EvenementDeplacement(double date, ChefPompier chef, Robot r, Case dest) {
		super(date, chef);
		this.robot = r;
		this.dest = dest;
	}

	public void execute() {
		this.robot.setPosition(this.dest);
	}
}
