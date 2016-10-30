package simulation.evenement;

public abstract class Evenement implements Comparable<Evenement> {

	protected long date;

	public Evenement(long date) {
		this.date = date;
	}

	public long getDate() {
		return this.date;
	}

	public abstract void execute();

	@Override
	public int compareTo(Evenement e) {
		if (e == null)
			throw new NullPointerException();

		return (int)(this.date - e.date);
	}
}


/* Evenements possibles :
 * - deplacement
 * - deverser eau
 * - remplir reservoir
 * - ...
 */
