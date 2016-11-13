package simulation;

import java.util.List;
import java.util.ArrayList;
import java.util.EnumMap;
import robot.*;
import chemin.*;
import enumerations.*;

/** Stock les différentes données d'une simulation */
public class DonneesSimulation {
	private Carte carteTerrain;
	private List<Incendie> listeIncendie;
	private List<Robot> listeRobot;
	private List<Case> listeEau;
	private PlusCourtChemin plusCourtChemin;

	/** Le constructeur initialise les listes */
	public DonneesSimulation(){
		this.listeIncendie = new ArrayList<Incendie>();
		this.listeRobot = new ArrayList<Robot>();
		this.listeEau = new ArrayList<Case>();
	}

	/** @return Plus court chemin entre source et dest
	 *  @param src Case source
	 *  @param dst Case destination
	 *  @param vitesse Mapping des vitesses associées aux terrains
	 **/
	public Chemin getChemin(Case src,Case dst,EnumMap<NatureTerrain, Double> vitesse){
		return this.plusCourtChemin.getChemin(src,dst,vitesse);
	}

	/** Initialise la carte de la simulation 
	 *  @param nbLignes Nombre de lignes de la carte
	 *  @param nbColonnes Nombre de colonnes de la carte
	 *  @param tailleCases Taille des cases de la carte
	 **/
	public void setCarte(int nbLignes,int nbColonnes,int tailleCases){
		this.carteTerrain = new Carte(nbLignes,nbColonnes,tailleCases);
	}

	/** Définit l'algorithme utilisé pour le plus court chemin: Dijkstra */
	public void setPlusCourtChemin(){
		this.plusCourtChemin = new Dijkstra(this.carteTerrain);
	}

	/** Initialise une case de la carte 
	 *  @param c Coordonnée de la case
	 *  @param terrain Nature du terrain de la case
	 **/
	public void setCase(Coordonnee c, NatureTerrain terrain){
		Case noeud = new Case(c,terrain);
		if(terrain == NatureTerrain.EAU)
			this.listeEau.add(noeud);
		this.carteTerrain.setCase(noeud);
	}

	/** Ajoute un incendie à la liste 
	 *  @param c Coordonnée de la case où se trouve l'incendie
	 *  @param intensite intensité de l'incendie
	 **/
	public void addIncendie(Coordonnee c,int intensite){
		this.listeIncendie.add(new Incendie(this.carteTerrain.getCase(c),intensite));
	}

	/** Ajoute un robot à la liste
	 *  @param c Coordonnée de la case où se trouve le robot
	 *  @param type Type du robot à ajouter
	 *  @throws IllegalArgumentException Type de robot non existant
	 **/
	public void addRobot(Coordonnee c,TypeRobot type){
		Robot robot_pompier;
		Case case_robot = this.carteTerrain.getCase(c);
		switch(type){
			case CHENILLES:
				robot_pompier = new RobotChenilles(case_robot);
				break;
			case DRONE:
				robot_pompier = new RobotDrone(case_robot);
				break;
			case ROUES:
				robot_pompier = new RobotRoues(case_robot);
				break;
			case PATTES:
				robot_pompier = new RobotPattes(case_robot);
				break;
			default :
				throw new IllegalArgumentException("Ce type de robot n'existe pas.");
		}
		this.listeRobot.add(robot_pompier);
	}

	/** Ajoute un robot à la liste en modifiant sa vitesse par défaut
	 *  @param c Coordonnée de la case où se trouve le robot
	 *  @param type Type du robot
	 *  @param vitesse Nouvelle vitesse du robot
	 *  @throws IllegalArgumentException Type de robot non existant
	 **/
	public void addRobot(Coordonnee c,TypeRobot type,int vitesse){
		Robot robot_pompier;
		Case case_robot = this.carteTerrain.getCase(c);
		double vitesse_d = (double) vitesse;
		switch(type){
			case CHENILLES:
				robot_pompier = new RobotChenilles(case_robot,vitesse_d);
				break;
			case DRONE:
				robot_pompier = new RobotDrone(case_robot,vitesse_d);
				break;
			case ROUES:
				robot_pompier = new RobotRoues(case_robot,vitesse_d);
				break;
			case PATTES:
				robot_pompier = new RobotPattes(case_robot,vitesse_d);
				break;
			default :
				throw new IllegalArgumentException("Ce type de robot n'existe pas.");
		}
		this.listeRobot.add(robot_pompier);
	}

	/** @return Carte de la simulation */
	public Carte getCarte(){
		return this.carteTerrain;
	}

	/** @return Un robot de la liste 
	 *  @param index Indice du robot dans la liste
	 **/
	public Robot getRobot(int index){
		return this.listeRobot.get(index);
	}

	/** @return Un incendie de la liste 
	 *  @param index Indice de l'incendie dans la liste
	 **/
	public Incendie getIncendie(int index) {
		return this.listeIncendie.get(index);
	}

	/** @return Nombre de lignes de la carte */
	public int getNbLignes(){
		return this.carteTerrain.getNbLignes();
	}
	
	/** @return Nombre de colonnes de la carte */
	public int getNbColonnes(){
		return this.carteTerrain.getNbColonnes();
	}

	/** @return NatureTerrain de la case (lig,col) 
	 *  @param lig Ligne de la case
	 *  @param col Colonne de la case
	 **/
	public NatureTerrain getNatureTerrain(int lig,int col){
		return this.carteTerrain.getNatureTerrain(lig,col);
	}

	/** @return Nombre d'incendie dans la liste */
	public int getNbIncendies(){
		return this.listeIncendie.size();
	}

	public List<Incendie> getListeIncendie() {
		return this.listeIncendie;
	}

	/** @return Nombre de robots dans la liste */
	public int getNbRobots(){
		return this.listeRobot.size();
	}

	/** @return Coordonnée d'un incendie dans la liste
	 *  @param index indice de l'incendie dans la liste
	 **/
	public Coordonnee getCoordonneeIncendie(int index){
		return this.listeIncendie.get(index).getCoordonnee();
	}

	/** @return Coordonnée d'un robot dans la liste
	 *  @param index indice du robot dans la liste
	 **/
	public Coordonnee getCoordonneeRobot(int index){
		return this.listeRobot.get(index).getCoordonnee();
	}
}
