package simulation.evenement;

import robot.*;
import simulation.*;

public class EvenementDeversementFin extends Evenement {

	private Robot robot;

	private Incendie incendie;

	public EvenementDeversementFin(double date, ChefPompier chef, Robot r, Incendie incendie) {
		super(date, chef);
		this.robot = r;
		this.incendie = incendie;
	}

	public void execute() {
		System.out.println("Déversement terminé");
		this.incendie.setEnExtinction(false);
		this.chef.calculDeplacementRemplissage(this.robot);
	}
}
