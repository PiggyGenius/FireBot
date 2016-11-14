package chemin;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import simulation.Case;

/** Représente un chemin par une liste de case ainsi qu'un temps de trajet */
public class Chemin {
	private HashSet<Destination> chemin;
	private double temps;

	/** Constructeur de chemin
	 *  @param predecesseur tableau 2-D des predecesseurs obtenue par Dijkstra
	 *  @param distance tableau 2-D des distances à la destination
	 *  @param src Case source
	 *  @param dst Case destination
	 **/
	public Chemin(Noeud src, Noeud dst){
		this.chemin = new HashSet<Destination>();
		Noeud noeud = dst;
		while(noeud != src){
			this.chemin.add(new Destination(noeud.getElement(),noeud.getPoids()));
			noeud = noeud.getPredecesseur();
		}
	}

	public Chemin(){
		this.chemin = new HashSet<Destination>();
		this.temps = 0;
	}

	/** @return Liste de case associée au chemin */
	public HashSet<Destination> getChemin(){
		return this.chemin;
	}

	/** @return Temps de trajet */
	public double getTemps() {
		return this.temps;
	}

	public void setTemps(double val) {
		this.temps = val;
	}
}
