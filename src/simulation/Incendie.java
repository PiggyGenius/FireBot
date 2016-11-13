package simulation;

/** Un incendie se trouve à une case et a une intensité */
public class Incendie {
	private Case position;
	private int intensite;

	/** Constructeur d'Incendie
	 *  @param case_terrain Case où se trouve l'incendie
	 *  @param intensite Intensité de l'incendie
	 **/
	public Incendie(Case case_terrain,int intensite){
		position = case_terrain;
		this.intensite = intensite;
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


	/** @param volume Quantite d'eau deversee */
	public void diminuerIntensite(int volume) {
		this.intensite -= volume;
		if (this.intensite < 0) {
			this.intensite = 0;
		}
	}
}
