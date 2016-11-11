package chemin;

import java.util.List;
import java.util.ArrayList;
import java.util.EnumMap;
import simulation.*;
import enumerations.NatureTerrain;

/** On réalise une classe statique avec un constructeur privé et des méthodes statiques. */
public class Dijkstra extends PlusCourtChemin {
	private Carte carte;
	private Case[][] ensembleCase;
	private Case[][] predecesseur;
	private double[][] distance;
	private List<Case> voisins;

	/** @param Carte carte du terrain de la simulation */
	public Dijkstra(Carte carte){
		this.carte = carte;
		int nb_lignes = this.carte.getNbLignes();
		int nb_colonnes = this.carte.getNbColonnes();
		this.voisins = new ArrayList<Case>();
		this.distance = new double[nb_lignes][nb_colonnes];
		this.predecesseur = new Case[nb_lignes][nb_colonnes];
		this.ensembleCase = new Case[nb_lignes][nb_colonnes];
		this.setEnsembleCase();
	}

	/** Remplit le tableau de Case */
	private void setEnsembleCase(){
		int nb_lignes = this.carte.getNbLignes();
		int nb_colonnes = this.carte.getNbColonnes();
		for(int i=0;i<nb_lignes;i++){
			for(int j=0;j<nb_colonnes;j++){
				this.ensembleCase[i][j] = this.carte.getCase(i,j);
			}
		}
	}

	/** Calcule le plus court chemin par Dijkstra
	 *  @param src Case de départ
	 *  @param dst Case de destination
	 **/
	@Override
	public Chemin getChemin(Case src,Case dst,EnumMap<NatureTerrain,Double> vitesse){
		int nb_lignes = this.carte.getNbLignes();
		int nb_colonnes = this.carte.getNbColonnes();
		int count = nb_lignes*nb_colonnes;
		this.init(src);
		for(int c=0;c<count;c++){
			Case noeud = this.getMin();
			if(this.distance[noeud.getLigne()][noeud.getColonne()] == Double.MAX_VALUE)
				return new Chemin(this.predecesseur,this.distance,src,dst);
			int i = noeud.getLigne();
			int j = noeud.getColonne();
			ensembleCase[i][j] = null;
			if(i-1>=0 && vitesse.get(this.carte.getCase(i-1,j).getNatureTerrain()) != -1)
				voisins.add(this.carte.getCase(i-1,j));
			if(i+1<nb_lignes && vitesse.get(this.carte.getCase(i+1,j).getNatureTerrain()) != -1)
				voisins.add(this.carte.getCase(i+1,j));
			if(j-1>=0 && vitesse.get(this.carte.getCase(i,j-1).getNatureTerrain()) != -1)
				voisins.add(this.carte.getCase(i,j-1));
			if(j+1<nb_colonnes && vitesse.get(this.carte.getCase(i,j+1).getNatureTerrain()) != -1)
				voisins.add(this.carte.getCase(i,j+1));
			for(Case noeud_voisin: voisins){
				setDistance(noeud,noeud_voisin,vitesse.get(noeud_voisin.getNatureTerrain()));
			}
		}
		this.setEnsembleCase();
		return new Chemin(this.predecesseur,this.distance,src,dst);
	}

	/** Initialise le tableau de distance à +inf */
	private void init(Case src){
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
		Case case_min = null;
		for(int i=0;i<this.ensembleCase.length;i++){
			for(int j=0;j<this.ensembleCase[0].length;j++){
				if(ensembleCase[i][j] != null){
					if(distance[i][j] < distance_min){
						distance_min = distance[i][j];
						case_min = this.ensembleCase[i][j];
					}
				}
			}
		}
		return case_min;
	}

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

	private double getPoids(Case src,Case dst,double vitesse){
		return (Math.abs(src.getLigne()-dst.getLigne())+Math.abs(src.getColonne()+dst.getColonne()))/vitesse;
	}
}
