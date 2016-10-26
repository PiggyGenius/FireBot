
/** classe abstraite Robot */
public abstract class Robot {

	private Case position;

	/** constructeur de la classe abstraite Robot
	 * @param position
	 * 	La position initiale du robot */
	public Robot(Case position) {
		setPosition(position);
	}


	/** @return Position courante */
	public Case getPosition() {
		return this.position;
	}


	/** @param position
	 * 	La nouvelle position du Robot */
	public void setPosition(Case position) {
		this.position = new Case(position, this.position.getNature());
	}


	/** 
	 * @param terrain
	 * 	La nature du terrain à traverser
	 * @return Vitesse du robot sur ce terrain */
	public abstract double getVitesse(NatureTerrain terrain);


	/** Action de deverser de l'eau sur la case courante 
	 * @param volume
	 * 	le volume d'eau a deverser */
	public abstract void deverserEau(int volume);


	/** Action de remplir le reservoir */
	public abstract void remplirReservoir();


	@Override
	public String toString() {
		return new String("Position : " + this.position);
	}
}
