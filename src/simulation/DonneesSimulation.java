package simulation;

import java.util.List;
import java.util.ArrayList;
import java.util.EnumMap;
import robot.*;
import chemin.*;
import enumerations.*;

public class DonneesSimulation {
	private Carte carteTerrain;
	private List<Incendie> listeIncendie = new ArrayList<Incendie>();
	private List<Robot> listeRobot = new ArrayList<Robot>();
	private PlusCourtChemin plusCourtChemin;

	public DonneesSimulation(){
	}

	public Chemin getChemin(Case src,Case dst,EnumMap<NatureTerrain, Double> vitesse){
		return this.plusCourtChemin.getChemin(src,dst,vitesse);
	}

	public void setCarte(int nbLignes,int nbColonnes,int tailleCases){
		this.carteTerrain = new Carte(nbLignes,nbColonnes,tailleCases);
	}

	public void setPlusCourtChemin(){
		this.plusCourtChemin = new Dijkstra(this.carteTerrain);
	}

	public void setCase(Coordonnee c, NatureTerrain terrain){
		this.carteTerrain.setCase(c,terrain);
	}

	public void addIncendie(Coordonnee c,int intensite){
		this.listeIncendie.add(new Incendie(this.carteTerrain.getCase(c),intensite));
	}

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

	public Carte getCarte(){
		return this.carteTerrain;
	}

	public Robot getRobot(int index){
		return this.listeRobot.get(index);
	}

	public int getNbLignes(){
		return this.carteTerrain.getNbLignes();
	}
	
	public int getNbColonnes(){
		return this.carteTerrain.getNbColonnes();
	}

	public NatureTerrain getNatureTerrain(int lig,int col){
		return this.carteTerrain.getNatureTerrain(lig,col);
	}

	public int getNbIncendies(){
		return this.listeIncendie.size();
	}

	public int getNbRobots(){
		return this.listeRobot.size();
	}

	public Coordonnee getCoordonneeIncendie(int index){
		return this.listeIncendie.get(index).getCoordonnee();
	}

	public Coordonnee getCoordonneeRobot(int index){
		return this.listeRobot.get(index).getCoordonnee();
	}
}
