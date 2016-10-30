package simulation.evenement;

public abstract class Evenement {

	protected long date;

	public Evenement(long date) {
		this.date = date;
	}

	public long getDate() {
		return this.date;
	}

	public abstract void execute();
}


/* Evenements possibles :
 * - deplacement
 * - deverser eau
 * - remplir reservoir
 * - ...
 */
