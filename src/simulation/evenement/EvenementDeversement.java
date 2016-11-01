package simulation.evenement;

import robot.*;
import simulation.*;

public class EvenementDeversement extends Evenement {

	private Robot robot;

	private int volume;

	public EvenementDeversement(long date, Robot r, int volume) {
		super(date);
		this.robot = r;
	}

	public void execute() {
		System.out.println("Deversement"); // DEBUG
		this.robot.deverserEau(this.volume);
	}
}
