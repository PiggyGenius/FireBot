package simulation.evenement;

import robot.*;
import simulation.*;

public class EvenementDeversement extends Evenement {

	private Robot robot;

	private int volume;

	private Incendie incendie;
	
	/** Constructeur d'evenement de deversement
	 * @param date la date de l'evenement
	 * @param chef le ChefPompier
	 * @param r le robot
	 * @param volume le volume d'eau a deverser
	 * @param incendie l'incendie */
	public EvenementDeversement(double date, ChefPompier chef, Robot r, int volume, Incendie incendie) {
		super(date, chef);
		this.robot = r;
		this.volume = volume;
		this.incendie = incendie;
	}

	/** Execute les actions associees a l'evenement */
	public void execute() {
		// System.out.println("Deversement");
		this.incendie.diminuerIntensite(this.volume);
		this.robot.diminuerQteReservoir(this.volume);
	}
}
