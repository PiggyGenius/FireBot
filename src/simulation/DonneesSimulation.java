package simulation;

import java.util.List;
import java.util.ArrayList;
import robot.*;
import enumerations.*;

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

	public void addRobot(String position){
	}
}
