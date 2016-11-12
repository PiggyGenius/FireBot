package simulation;

import enumerations.*;

/** classe Case */
public class Case {
	private Coordonnee coord;
	private NatureTerrain nature;


	/** Constructeur de Case avec coordonnée
	 * @param coord
	 * 	coordonnees de la case
	 * @param terrain
	 * 	nature du terrain de la case */
	public Case (Coordonnee coord, NatureTerrain terrain) {
		this.coord = new Coordonnee(coord);
		this.nature = terrain;
	}

	/** Constructeur de Case avec couple ligne,colonne
	 *  @param ligne Indice de ligne de la case
	 *  @param colonne Indice de la colonne de la case
	 *  @param terrain Nature du terrain de la case
	 **/
	public Case (int ligne,int colonne, NatureTerrain terrain) {
		this.coord = new Coordonnee(ligne,colonne);
		this.nature = terrain;
	}

	/** @return Ligne */
	public int getLigne() {
		return this.coord.getLigne();
	}


	/** @return Colonne */
	public int getColonne() {
		return this.coord.getColonne();
	}

	/** @return coordonnée de la case */
	public Coordonnee getCoordonnee(){
		return new Coordonnee(this.coord);
	}

	/** @return Nature du terrain */
	public NatureTerrain getNatureTerrain() {
		return this.nature;
	}


	/** @return indice i,j de la case sous le format (i,j) */
	@Override
	public String toString() {
		return new String(this.coord.toString());
	}

}
