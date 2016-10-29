package simulation;

import java.util.List;
import java.util.ArrayList;
import robot.*;
import enumerations.*;
import robot.*;

public class DonneesSimulation {
	private Carte carteTerrain;
	private List<Incendie> listeIncendie = new ArrayList<Incendie>();
	private List<Robot> listeRobot = new ArrayList<Robot>();
	private int nbIncendies;
	private int nbRobots;

	public DonneesSimulation(){
	}

	public void setCarte(int nbLignes,int nbColonnes,int tailleCases){
		this.carteTerrain = new Carte(nbLignes,nbColonnes,tailleCases);
	}

	public void setCase(Coordonnee c, NatureTerrain terrain){
		this.carteTerrain.setCase(c,terrain);
	}

	public void setNbIncendies(int nbIncendies){
		this.nbIncendies = nbIncendies;
	}

	public void addIncendie(Coordonnee c,int intensite){
		this.listeIncendie.add(new Incendie(this.carteTerrain.getCase(c),intensite));
	}

	public void setNbRobots(int nbRobots){
		this.nbRobots = nbRobots;
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
}
