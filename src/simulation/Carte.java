package simulation;

import enumerations.*;

public class Carte {
	private int tailleCases;
	private Case[][] grilleCase;

	public Carte(int nbLignes,int nbColonnes,int tailleCases){
		this.grilleCase = new Case[nbLignes][nbColonnes];
		this.tailleCases = tailleCases;
	}

	public void evenement(long date){
	}

	public int getNbLignes(){
		return grilleCase[0].length;
	}

	public int getNbColonnes(){
		return grilleCase.length;
	}

	public int getTailleCases(){
		return this.tailleCases;
	}

	public Case getCase(int lig, int col){
		if(lig < 0 || lig > this.getNbLignes() || col < 0 || col > this.getNbColonnes())
			throw new IllegalArgumentException();
		return this.grilleCase[lig][col];
	}

	public Case getCase(Coordonnee c){
		int lig = c.getLigne();
		int col = c.getColonne();
		if(lig < 0 || lig > this.getNbLignes() || col < 0 || col > this.getNbColonnes())
			throw new IllegalArgumentException();
		return this.grilleCase[lig][col];
	}

	public void setCase(Coordonnee c, NatureTerrain terrain){
		grilleCase[c.getLigne()][c.getColonne()] = new Case(c,terrain);
	}

	private Coordonnee getCoordonnee(Case src, Direction dir){
		Coordonnee voisin = new Coordonnee(src.getLigne(),src.getColonne());
		int x = voisin.getLigne();
		int y = voisin.getColonne();
		switch(dir){
			case NORD:
				voisin.setLigne(x+1);
				break;
			case SUD:
				voisin.setLigne(x-1);
				break;
			case EST:
				voisin.setColonne(y+1);
				break;
			case OUEST:
				voisin.setColonne(y-1);
				break;
			default :
				throw new IllegalArgumentException("dir n'est pas une Direction");
		}
		return voisin;
	}

	public boolean voisinExiste(Case src, Direction dir){
		Coordonnee voisin = getCoordonnee(src,dir);
		int x = voisin.getLigne();
		int y = voisin.getColonne();
		if(x < 0 || x > getNbLignes() || y < 0 || y > getNbColonnes())
			return false;
		return true;
	}

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

	public NatureTerrain getNatureTerrain(int lig,int col){
		if(lig < 0 || lig > getNbLignes() || col < 0 || col > getNbColonnes())
			throw new IllegalArgumentException("Cette case n'existe pas");
		return grilleCase[lig][col].getNatureTerrain();
	}
}
