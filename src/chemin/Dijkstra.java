package chemin;

import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.EnumMap;
import java.util.PriorityQueue;
import simulation.*;
import enumerations.NatureTerrain;

/** Algorithme Dijkstra de calcule du plus court chemin */
public class Dijkstra extends PlusCourtChemin {
	private PriorityQueue<Noeud> ensembleNoeud;
	private Noeud[][] tableau_noeud;

	/** @param carte carte du terrain de la simulation */
	public Dijkstra(Carte carte){
		this.tableau_noeud = new Noeud[carte.getNbLignes()][carte.getNbColonnes()];
		this.stockCarte(carte);
	}

	/** @param carte à stocker dans l'algorithme sous forme de noeuds */
	public void stockCarte(Carte carte){
		for(int i=0;i<tableau_noeud.length;i++){
			for(int j=0;j<tableau_noeud[0].length;j++){
				this.tableau_noeud[i][j] = new Noeud(carte.getCase(i,j));
			}
		}
	}

	/** Initialise le poids et prédecesseur du tableau de noeud
	 *  @param vitesse Mapping des vitesses associées aux terrains
	 **/
	private void setEnsembleNoeud(Case src,EnumMap<NatureTerrain,Double> vitesse){
		int nb_lignes = this.tableau_noeud.length;
		int nb_colonnes = this.tableau_noeud[0].length;
		Case noeud = null;
		for(int i=0;i<nb_lignes;i++){
			for(int j=0;j<nb_colonnes;j++){
				this.tableau_noeud[i][j].setPoids(Double.MAX_VALUE);
				this.tableau_noeud[i][j].setPredecesseur(null);
			}
		}
	}

	/** Calcule le plus court chemin par Dijkstra
	 *  @param src Case de départ
	 *  @param dst Case de destination
	 *  @param vitesse Mapping des vitesses associées aux terrains
	 *  @throws NoSuchElementException La case destination n'est pas accessible
	 *  @return chemin Liste de destination à suivre pour se rendre à l'objectif + temps
	 **/
	@Override
	public Chemin getChemin(Case src,Case dst,EnumMap<NatureTerrain,Double> vitesse){
		if(src == dst)
			return new Chemin();
		if(vitesse.get(dst.getNatureTerrain()) == 0.0)
			return null;
		int src_i = src.getLigne(); int src_j = src.getColonne();
		int dst_i = dst.getLigne(); int dst_j = dst.getColonne();

		this.setEnsembleNoeud(src,vitesse);
		this.tableau_noeud[src_i][src_j].setPoids(0.0);
		this.ensembleNoeud = new PriorityQueue<Noeud>();
		this.ensembleNoeud.add(this.tableau_noeud[src_i][src_j]);
		while(this.ensembleNoeud.peek() != null){
			Noeud min = this.ensembleNoeud.poll();
			if(min.getPoids() == Double.MAX_VALUE)
				throw new NoSuchElementException();
			this.setDistanceVoisins(min,vitesse);
		}
		return new Chemin(this.tableau_noeud[src_i][src_j],this.tableau_noeud[dst_i][dst_j]);
	}

	/** Met à jour la distance des voisins
	 *  @param noeud Case de départ pour déterminer les voisins
	 **/
	private void setDistanceVoisins(Noeud noeud,EnumMap<NatureTerrain,Double> vitesse){
		int nb_lignes = this.tableau_noeud.length;
		int nb_colonnes = this.tableau_noeud[0].length;
		int i = noeud.getLigne();
		int j = noeud.getColonne();
		Noeud noeud_voisin;
		if(i-1>=0){
			noeud_voisin = this.tableau_noeud[i-1][j];
			setDistance(noeud,noeud_voisin,vitesse.get(noeud_voisin.getNatureTerrain()));
		}
		if(i+1<nb_lignes){
			noeud_voisin = this.tableau_noeud[i+1][j];
			setDistance(noeud,noeud_voisin,vitesse.get(noeud_voisin.getNatureTerrain()));
		}
		if(j-1>=0){
			noeud_voisin = this.tableau_noeud[i][j-1];
			setDistance(noeud,noeud_voisin,vitesse.get(noeud_voisin.getNatureTerrain()));
		}
		if(j+1<nb_colonnes){
			noeud_voisin = this.tableau_noeud[i][j+1];
			setDistance(noeud,noeud_voisin,vitesse.get(noeud_voisin.getNatureTerrain()));
		}
	}

	/** Mise a jour du tableau de distance des noeuds 
	 *  @param src Case source
	 *  @param dst Case destination
	 *  @param vitesse Vitesse du robot sur la Case destination
	 **/
	private void setDistance(Noeud src,Noeud dst,double vitesse){
		if(vitesse == 0.0)
			return;
		int src_i = src.getLigne();
		int src_j = src.getColonne();
		int dst_i = dst.getLigne();
		int dst_j = dst.getColonne();
		double poids = this.getPoids(src,dst,vitesse);
		if(dst.getPoids() > src.getPoids()+poids){
			dst.setPoids(src.getPoids()+poids);
			dst.setPredecesseur(src);
			this.ensembleNoeud.add(dst);
		}
	}

	/** Calcul le poids d'un arc
	 *  @param src Case source
	 *  @param dst Case destination
	 *  @param vitesse Vitesse du robot sur la Case destination
	 *  @return distance(src,dst)/vitesse
	 */
	private double getPoids(Noeud src,Noeud dst,double vitesse){
		return (Math.abs(src.getLigne()-dst.getLigne())+Math.abs(src.getColonne()-dst.getColonne()))/vitesse;
	}
}
