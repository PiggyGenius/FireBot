package chemin;

import simulation.Case;
import enumerations.NatureTerrain;

/** Permet l'utilisation de la Priority Queue avec une couche d'abstraction supplémentaire */
public class Noeud implements Comparable<Noeud> {
	private Case element;
	private Noeud predecesseur;
	private double poids;

	/** Constructeur de noeud pour la liste de priorité 
	 *  @param element Case à laquelle le noeud fait référence
	 **/
	public Noeud(Case element){
		this.element = element;
		this.poids = Double.MAX_VALUE;
		this.predecesseur = null;
	}

	/** @return Indice de ligne de l'élément stocké */
	public int getLigne(){
		return this.element.getLigne();
	}

	/** @return Indice de ligne de l'élément stocké */
	public int getColonne(){
		return this.element.getColonne();
	}

	/** @return Nature du terrain de l'élément stocké */
	public NatureTerrain getNatureTerrain(){
		return this.element.getNatureTerrain();
	}

	/** @param predecesseur Predecesseur du noeud stocké */
	public void setPredecesseur(Noeud predecesseur){
		this.predecesseur = predecesseur;
	}

	/** @return Prédecesseur du noeud stocké */
	public Noeud getPredecesseur(){
		return this.predecesseur;
	}

	/** @param poids Temps du trajet jusqu'à la case sous-jacente */
	public void setPoids(double poids){
		this.poids = poids;
	}

	/** @return Poids du noeud */
	public double getPoids(){
		return this.poids;
	}

	/** @param element Element sous-jacent au noeud */
	public void setElement(Case element){
		this.element = element;
	}

	/** @return Case du noeud */
	public Case getElement(){
		return this.element;
	}

	@Override
	public int compareTo(Noeud n){
		if(this.poids > n.getPoids())
			return 1;
		else if(this.poids == n.getPoids())
			return 0;
		else
			return -1;
	}
}
