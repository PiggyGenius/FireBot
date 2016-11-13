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
		// appeler methode du chef pompier qui va
		// passer le statut de ce robot à libre
		return;
	}
}
