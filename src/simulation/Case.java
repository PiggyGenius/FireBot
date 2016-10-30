package simulation;

import enumerations.*;

/** classe Case */
public class Case {
	private Coordonnee coord;
	private NatureTerrain nature;


	/** constructeur de Case
	 * @param coord
	 * 	coordonnees de la case
	 * @param terrain
	 * 	nature du terrain de la case */
	public Case (Coordonnee coord, NatureTerrain terrain) {
		this.coord = new Coordonnee(coord);
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

	public Coordonnee getCoordonnee(){
		return new Coordonnee(this.coord);
	}

	/** @return Nature du terrain */
	public NatureTerrain getNatureTerrain() {
		return this.nature;
	}


	@Override
	public String toString() {
		return new String(this.coord.toString());
	}

}
