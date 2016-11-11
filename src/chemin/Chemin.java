package chemin;

import java.util.List;
import java.util.ArrayList;
import simulation.Case;

public class Chemin {
	private List<Case> chemin;
	private double temps;


	public Chemin(Case[][] predecesseur,double[][] distance,Case src, Case dst){
		this.chemin = new ArrayList<Case>();
		this.temps = 0.0;
		Case noeud = dst;
		// Pourquoi ? Je ne sais pas
		while(noeud.getLigne() != src.getLigne() || noeud.getColonne() != src.getColonne()){
			this.chemin.add(noeud);
			System.out.println(noeud);
			this.temps += distance[noeud.getLigne()][noeud.getColonne()];
			noeud = predecesseur[noeud.getLigne()][noeud.getColonne()];
		}
	}
}
