package simulation.evenement;

import robot.*;
import simulation.*;

public class EvenementDeversementFin extends Evenement {

	private Robot robot;

	public EvenementDeversementFin(double date, ChefPompier chef, Robot r) {
		super(date, chef);
		this.robot = r;
	}

	public void execute() {
		System.out.println("Déversement terminé");
		this.chef.calculRemplissage(this.robot);
	}
}
