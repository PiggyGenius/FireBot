package chemin;

import java.util.List;
import java.util.ArrayList;
import simulation.Case;

/** Représente un chemin par une liste de case ainsi qu'un temps de trajet */
public class Chemin {
	private List<Case> chemin;
	private double temps;

	/** Constructeur de chemin
	 *  @param predecesseur tableau 2-D des predecesseurs obtenue par Dijkstra
	 *  @param distance tableau 2-D des distances à la destination
	 *  @param src Case source
	 *  @param dst Case destination
	 **/
	public Chemin(Case[][] predecesseur,double[][] distance,Case src, Case dst){
		this.chemin = new ArrayList<Case>();
		this.temps = 0.0;
		Case noeud = dst;
		// Pourquoi ? Je ne sais pas
		while(noeud.getLigne() != src.getLigne() || noeud.getColonne() != src.getColonne()){
			this.chemin.add(noeud);
			this.temps += distance[noeud.getLigne()][noeud.getColonne()];
			noeud = predecesseur[noeud.getLigne()][noeud.getColonne()];
		}
	}

	/** @return Liste de case associée au chemin */
	public List<Case> getChemin(){
		return this.chemin;
	}
}
