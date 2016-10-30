package simulation.evenement;

import robot.*;
import simulation.*;

public class EvenementDeplacement extends Evenement {

	private Robot robot;

	private Case dest;

	public EvenementDeplacement(long date, Robot r, Case dest) {
		super(date);
		this.robot = r;
		this.dest = dest;
	}

	public void execute() {
		System.out.println("Destination : " + this.dest.toString());
	}
}
