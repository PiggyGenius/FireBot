package simulation;

/** Un incendie se trouve à une case et a une intensité */
public class Incendie {
	private Case position;
	private int intensite;

	/** Construceut d'Incendie
	 *  @param case_terrain Case où se trouve l'incendie
	 *  @param intensite Intensité de l'incendie
	 **/
	public Incendie(Case case_terrain,int intensite){
		position = case_terrain;
		this.intensite = intensite;
	}

	/** @return Coordonnée de l'incendie */
	public Coordonnee getCoordonnee(){
		return this.position.getCoordonnee();
	}
}
