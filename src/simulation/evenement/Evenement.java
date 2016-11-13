package simulation.evenement;

import simulation.*;

public abstract class Evenement implements Comparable<Evenement> {

	protected double date;

	protected ChefPompier chef;

	public Evenement(double date, ChefPompier chef) {
		this.date = date;
		this.chef = chef;
	}

	public double getDate() {
		return this.date;
	}

	public abstract void execute();

	@Override
	public int compareTo(Evenement e) {
		if (e == null)
			throw new NullPointerException();
		if(this.date < e.date)
			return -1;
		else if(this.date == e.date)
			return 0;
		else
			return 1;
	}
}


