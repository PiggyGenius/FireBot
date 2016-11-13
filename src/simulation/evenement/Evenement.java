package simulation.evenement;

public abstract class Evenement implements Comparable<Evenement> {

	protected double date;

	public Evenement(double date) {
		this.date = date;
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


/* Types d'evenements (absolument pas sur de ça) :
 * - deplacement
 * - deversement
 * - remplissage
 */
