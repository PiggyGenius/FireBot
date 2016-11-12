package simulation;

import enumerations.*;

/** Représente une carte comme une matrice de cases */
public class Carte {
	private int tailleCases;
	private Case[][] grilleCase;

	/** Constructeur de carte 
	 *  @param nbLignes Nombre de lignes de la matrice
	 *  @param nbColonnes Nombre de colonnes de la matrice
	 *  @param tailleCases Taille d'une case en mètre
	 **/
	public Carte(int nbLignes,int nbColonnes,int tailleCases){
		this.grilleCase = new Case[nbLignes][nbColonnes];
		this.tailleCases = tailleCases;
	}

	public void evenement(long date){
	}

	/** @return Nombre de lignes de la carte */
	public int getNbLignes(){
		return grilleCase[0].length;
	}

	/** @return Nombre de colonnes de la carte */
	public int getNbColonnes(){
		return grilleCase.length;
	}

	/** @return Taille des cases de la carte */
	public int getTailleCases(){
		return this.tailleCases;
	}

	/** @return Case à la position lig,col de la matrice
	 *  @param lig Indice ligne de la case
	 *  @param col Indice colonne de la case
	 *  @throws IllegalArgumentException lig,col n'est pas dans la dimension de la matrice
	 **/
	public Case getCase(int lig, int col){
		if(lig < 0 || lig > this.getNbLignes() || col < 0 || col > this.getNbColonnes())
			throw new IllegalArgumentException();
		return this.grilleCase[lig][col];
	}

	/** @return Case à la coordonée c de la matrice
	 *  @param c Coordonnee de la case
	 **/
	public Case getCase(Coordonnee c){
		return this.getCase(c.getLigne(), c.getColonne());
	}

	/** Ajoute une case dans la matrice 
	 *  @param c Coordonnée de la nouvelle case
	 *  @param terrain Nature du terrain de la case
	 **/
	public void setCase(Coordonnee c, NatureTerrain terrain){
		grilleCase[c.getLigne()][c.getColonne()] = new Case(c,terrain);
	}

	/** @return Coordonnée de la case voisine à src
	 *  @param src Case source
	 *  @param dir Direction dans laquelle chercher le voisin
	 *  @throws IllegalArgumentException dir n'est pas une direction valide
	 **/
	private Coordonnee getCoordonnee(Case src, Direction dir){
		Coordonnee voisin = new Coordonnee(src.getLigne(),src.getColonne());
		switch(dir){
			case NORD:
				voisin.setLigne(voisin.getLigne()-1);
				break;
			case SUD:
				voisin.setLigne(voisin.getLigne()+1);
				break;
			case EST:
				voisin.setColonne(voisin.getColonne()+1);
				break;
			case OUEST:
				voisin.setColonne(voisin.getColonne()-1);
				break;
			default :
				throw new IllegalArgumentException("dir n'est pas une Direction");
		}
		return voisin;
	}

	/** @return true si le voisin existe, false sinon
	 *  @param src Case source
	 *  @param dir Direction dans laquelle chercher le voisin
	 **/
	public boolean voisinExiste(Case src, Direction dir){
		Coordonnee voisin = getCoordonnee(src,dir);
		int x = voisin.getLigne();
		int y = voisin.getColonne();
		if(x < 0 || x > getNbLignes() || y < 0 || y > getNbColonnes())
			return false;
		return true;
	}

	/** @return voisin de la case source
	 *  @param src Case source
	 *  @param dir Direction dans laquelle chercher le voisin
	 *  @throws IllegalArgumentException Le voisin n'existe pas
	 **/
	public Case getVoisin(Case src, Direction dir){
		if(voisinExiste(src,dir)){
			Coordonnee voisin = getCoordonnee(src,dir);
			return grilleCase[voisin.getLigne()][voisin.getColonne()];
		}
		else {
			System.out.println("Cette case n'a pas de voisin dans cette direction.");
			throw new IllegalArgumentException();
		}
	}


	/** @return Nature du terrain de la case lig,col
	 *  @param lig Ligne de la case
	 *  @param col Colonne de la case
	 *  @throws IllegalArgumentException La case n'existe pas
	 **/
	public NatureTerrain getNatureTerrain(int lig,int col){
		if(lig < 0 || lig > getNbLignes() || col < 0 || col > getNbColonnes())
			throw new IllegalArgumentException("Cette case n'existe pas");
		return grilleCase[lig][col].getNatureTerrain();
	}
}
