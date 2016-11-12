package chemin;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.EnumMap;
import simulation.*;
import enumerations.NatureTerrain;

/** On réalise une classe statique avec un constructeur privé et des méthodes statiques. */
public class Dijkstra extends PlusCourtChemin {
	private Carte carte;
	private HashSet<Case> ensembleNoeud;
	private Case[][] predecesseur;
	private double[][] distance;

	/** @param carte carte du terrain de la simulation */
	public Dijkstra(Carte carte){
		this.carte = carte;
		int nb_lignes = this.carte.getNbLignes();
		int nb_colonnes = this.carte.getNbColonnes();
		this.distance = new double[nb_lignes][nb_colonnes];
		this.predecesseur = new Case[nb_lignes][nb_colonnes];
	}

	/** Remplit le HashSet de Case avec les références de la carte */
	private void setEnsembleNoeud(EnumMap<NatureTerrain,Double> vitesse){
		int nb_lignes = this.carte.getNbLignes();
		int nb_colonnes = this.carte.getNbColonnes();
		this.ensembleNoeud = new HashSet<Case>(nb_lignes*nb_colonnes);
		Case noeud = null;
		for(int i=0;i<nb_lignes;i++){
			for(int j=0;j<nb_colonnes;j++){
				noeud = this.carte.getCase(i,j);
				if(vitesse.get(noeud.getNatureTerrain()) != 0.0)
					this.ensembleNoeud.add(this.carte.getCase(i,j));
			}
		}
	}

	/** Calcule le plus court chemin par Dijkstra
	 *  @param src Case de départ
	 *  @param dst Case de destination
	 *  @param vitesse Mapping des vitesses associées aux terrains
	 *  @return chemin Liste de case à suivre pour se rendre à l'objectif + temps
	 **/
	@Override
	public Chemin getChemin(Case src,Case dst,EnumMap<NatureTerrain,Double> vitesse){
		List<Case> voisins = new ArrayList<Case>();

		this.setEnsembleNoeud(vitesse);
		this.initDistance(src);
		Iterator<Case> noeud = this.ensembleNoeud.iterator();
		while(noeud.hasNext()){
			Case min = this.getMin();
			if(min == null || this.distance[min.getLigne()][min.getColonne()] == Double.MAX_VALUE)
				return new Chemin(this.predecesseur,this.distance,src,dst);
			this.ensembleNoeud.remove(min);
			this.ajouteVoisins(min,voisins);
			for(Case noeud_voisin: voisins){
				setDistance(min,noeud_voisin,vitesse.get(noeud_voisin.getNatureTerrain()));
			}
		}
		return new Chemin(this.predecesseur,this.distance,src,dst);
	}

	/** Ajoute les voisins à la liste
	 *  @param noeud Case de départ pour déterminer les voisins
	 *  @param voisins Liste de voisins
	 **/
	private void ajouteVoisins(Case noeud,List<Case> voisins){
		int nb_lignes = this.carte.getNbLignes();
		int nb_colonnes = this.carte.getNbColonnes();
		int i = noeud.getLigne();
		int j = noeud.getColonne();
		if(i-1>=0)
			voisins.add(this.carte.getCase(i-1,j));
		if(i+1<nb_lignes)
			voisins.add(this.carte.getCase(i+1,j));
		if(j-1>=0)
			voisins.add(this.carte.getCase(i,j-1));
		if(j+1<nb_colonnes)
			voisins.add(this.carte.getCase(i,j+1));
	}

	/** Initialise le tableau de distance à +inf 
	 *  @param src Case source
	 **/
	private void initDistance(Case src){
		int nb_lignes = this.distance.length;
		int nb_colonnes = this.distance[0].length;
		for(int i=0;i<nb_lignes;i++){
			for(int j=0;j<nb_colonnes;j++){
				this.distance[i][j] = Double.MAX_VALUE;
			}
		}
		this.distance[src.getLigne()][src.getColonne()] = 0;
	}

	/** Trouve la distance minimale entre deux cases 
	 *  @return Case la plus proche de la source
	 **/
	private Case getMin(){
		double distance_min = Double.MAX_VALUE;
		Iterator<Case> noeud_set = this.ensembleNoeud.iterator();
		Case case_min = null;
		Case noeud = null;
		while(noeud_set.hasNext()){
			noeud = noeud_set.next();
			if(distance[noeud.getLigne()][noeud.getColonne()] < distance_min){
				distance_min = distance[noeud.getLigne()][noeud.getColonne()];
				case_min = noeud;
			}
		}
		return case_min;
	}

	/** Mise a jour du tableau de distance des noeuds 
	 *  @param src Case source
	 *  @param dst Case destination
	 *  @param vitesse Vitesse du robot sur la Case destination
	 **/
	private void setDistance(Case src,Case dst,double vitesse){
		int src_i = src.getLigne();
		int src_j = src.getColonne();
		int dst_i = dst.getLigne();
		int dst_j = dst.getColonne();
		double poids = this.getPoids(src,dst,vitesse);
		if(poids != Double.MAX_VALUE && distance[dst_i][dst_j] > distance[src_i][src_j]+poids){
			distance[dst_i][dst_j] = distance[src_i][src_j]+poids;
			predecesseur[dst_i][dst_j] = src;
		}
	}

	/** Calcul le poids d'un arc
	 *  @param src Case source
	 *  @param dst Case destination
	 *  @param vitesse Vitesse du robot sur la Case destination
	 *  @return distance(src,dst)/vitesse
	 */
	private double getPoids(Case src,Case dst,double vitesse){
		return (Math.abs(src.getLigne()-dst.getLigne())+Math.abs(src.getColonne()-dst.getColonne()))/vitesse;
	}
}
