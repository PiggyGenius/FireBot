package simulation.evenement;

import robot.*;
import simulation.*;

public class EvenementDeplacement extends Evenement {

	private Robot robot;

	private Case dest;

	/** Constructeur d'event de deplacement
	 * @param date date de l'evenement
	 * @param chef le ChefPompier
	 * @param r le robot
	 * @param dest la destination du deplacement */
	public EvenementDeplacement(double date, ChefPompier chef, Robot r, Case dest) {
		super(date, chef);
		this.robot = r;
		this.dest = dest;
	}

	/** Execute les actions associees a l'evenement */
	public void execute() {
		this.robot.setPosition(this.dest);
	}
}
