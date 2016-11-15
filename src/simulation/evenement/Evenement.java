package simulation.evenement;

import simulation.*;

public abstract class Evenement implements Comparable<Evenement> {

	protected double date;

	protected ChefPompier chef;

	/** Constructeur d'evenement
	 * @param date la date de l'evenement
	 * @param chef le ChefPompier */
	public Evenement(double date, ChefPompier chef) {
		this.date = date;
		this.chef = chef;
	}

	/** @return date de l'evenement */
	public double getDate() {
		return this.date;
	}

	/** Execute les actions associees a l'evenement */
	public abstract void execute();

	/** Methode de comparaison de dates d'evenement
	 * @param e l'evenement a comparer
	 * @return 0 si meme date, -1 si this est plus prioritaire, 1 sinon */
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


