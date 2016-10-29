public class Carte {
	private int taille_case;
	private Case[][] grille_case;

	public Carte(int taille_case){
		this.taille_case = taille_case;
	}

	public void evenement(long date){
	}

	public int getNbLignes(){
		return grille_case[0].length;
	}

	public int getNbColonnes(){
		return grille_case.length;
	}

	public int getTailleCases(){
		return this.taille_case;
	}

	public Case getCase(int lig, int col){
		if(lig < 0 || lig > this.getNbLignes() || col < 0 || col > this.getNbColonnes())
			throw new IllegalArgumentException();
		return this.grille_case[lig][col];
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
				System.out.println("dir n'est pas une Direction");
				throw new IllegalArgumentException();
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
			return grille_case[voisin.getLigne()][voisin.getColonne()];
		}
		else {
			System.out.println("Cette case n'a pas de voisin dans cette direction.");
			throw new IllegalArgumentException();
		}
	}
}
