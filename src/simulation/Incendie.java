package simulation;

/** Un incendie se trouve à une case et a une intensité */
public class Incendie {

	private Case position;

	private int intensite;

	private boolean enExtinction;

	/** Constructeur d'Incendie
	 *  @param case_terrain Case où se trouve l'incendie
	 *  @param intensite Intensité de l'incendie
	 **/
	public Incendie(Case case_terrain,int intensite){
		this.position = case_terrain;
		this.intensite = intensite;
		this.enExtinction = false;
	}

	/** Constructeur d'Incendie
	 * @param i incendie a copier */
	public Incendie(Incendie i) {
		this(i.getCase(), i.getIntensite());
	}


	/** @return Case de l'incendie */
	public Case getCase() {
		return this.position;
	}


	/** @return Coordonnée de l'incendie */
	public Coordonnee getCoordonnee(){
		return this.position.getCoordonnee();
	}


	/** @return Intensite de l'incendie */
	public int getIntensite() {
		return this.intensite;
	}

	/** @return true si le feu est en cours d'extinction */
	public boolean getEnExtinction() {
		return this.enExtinction;
	}

	/** @param val true si le feu passe en extinction */
	public void setEnExtinction(boolean val) {
		this.enExtinction = val;
	}


	/** @param volume Quantite d'eau deversee */
	public void diminuerIntensite(int volume) {
		this.intensite -= volume;
		if (this.intensite < 0) {
			this.intensite = 0;
		}
	}
}
