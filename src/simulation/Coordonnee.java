package simulation;

/** Permet de stocker les couples ligne,colonne avec un seul objet */
public class Coordonnee {
	private int x;
	private int y;

	/** Constructeur de Coordonnee avec un couple (i,j)
	 *  @param x indice x
	 *  @param y indice y
	 **/
	public Coordonnee(int x,int y){
		this.x = x;
		this.y = y;
	}

	/** Construct de Coordonnee avec une coordonnee
	 *  @param c Coordonnée préexistante
	 **/
	public Coordonnee(Coordonnee c){
		this.setLigne(c.getLigne());
		this.setColonne(c.getColonne());
	}

	/** @return champ x de la coordonnée */
	public int getLigne(){
		return this.x;
	}

	/** @return champ y de la coordonnée */
	public int getColonne(){
		return this.y;
	}

	/** Ecrase le champ x de la coordonnée 
	 *  @param x nouvelle valeur x
	 *  @throws IllegalArgumentException x doit être supérieur à 0
	 **/
	public void setLigne(int x){
		if(x < 0)
			throw new IllegalArgumentException();
		this.x = x;
	}

	/** Ecrase le champ y de la coordonnée 
	 *  @param y nouvelle valeur y
	 *  @throws IllegalArgumentException y doit être supérieur à 0
	 **/
	public void setColonne(int y){
		if(y < 0)
			throw new IllegalArgumentException();
		this.y = y;
	}

	/** @return (x,y) */
	@Override
	public String toString(){
		return "("+this.x+";"+this.y+")";
	}
}
