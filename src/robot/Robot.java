package robot;

import java.lang.Double;
import java.util.EnumMap;
import java.util.Iterator;

import enumerations.*;
import simulation.*;
import chemin.*;

/** classe abstraite Robot */
public abstract class Robot {

	/* ######################### Attributs ######################### */

	/* position courante du robot */
	protected Case position;

	/* vitesses du robot pour chaque élément (0 si inaccessible) */
	protected EnumMap<NatureTerrain, Double> vitesse;

	/* capacité du réservoir (0 si infini) */
	protected int capaciteReservoir;

	/* contenance courante du reservoir */
	protected int qteReservoir;

	/* temps de remplissage du réservoir en secondes (0 si reservoir infini) */
	protected int tempsRemplissage;

	/* nb de litres versés par temps unitaire */
	protected int litresUnitaire;

	/* temps unitaire de remplissage */
	protected int tempsUnitaire;

	/* distance a laquelle le robot doit se trouver d'un pt d'eau (0 ou 1) */
	protected int distanceRemplissage;

	/* indique si le robot est disponible ou non */
	protected boolean dispo;


	/* ######################### Constructeur ######################### */

	/** constructeur de la classe abstraite Robot
	 * @param position La position initiale du robot 
	 **/
	public Robot(Case position) {
		this.setPosition(position);
		vitesse = new EnumMap<NatureTerrain, Double>(NatureTerrain.class);
	}

	/* ######################### Getters / Setters ######################### */

	/** @return Position courante */
	public Case getPosition() {
		return this.position;
	}

	public Coordonnee getCoordonnee(){
		return this.position.getCoordonnee();
	}

	/** @param position	La nouvelle position du Robot */
	public void setPosition(Case position) {
		this.position = position;
	}

	public int getCapaciteReservoir() {
		return this.capaciteReservoir;
	}

	public int getTempsRemplissage() {
		return this.tempsRemplissage;
	}

	public int getLitresUnitaire() {
		return this.litresUnitaire;
	}

	public int getTempsUnitaire() {
		return this.tempsUnitaire;
	}

	public int getDistanceRemplissage() {
		return this.distanceRemplissage;
	}

	public boolean getDispo() {
		return this.dispo;
	}

	/** 
	 * @param terrain
	 * 	La nature du terrain à traverser
	 * @return Vitesse du robot sur ce terrain */
	public double getVitesse(NatureTerrain terrain) {
		return this.vitesse.get(terrain);
	}


	/* ######################### Evenements  ######################### */

	public void planifierAction(Chemin chemin) {
		planifierDeplacement();
		planifierDeversement();
		planifierRemplissage();
	}


	/** Action de se deplacer */
	public static void planifierDeplacement() {
		// TODO	: ajouter la liste d'evénements elémentaires nécessaires au déplacement
		return; 
	}


	/** Action de deverser de l'eau */
	public static void planifierDeversement() {
		// TODO : ajouter evenement de deversement
		return;
	}


	/** Action de remplir le reservoir */
	public static void planifierRemplissage() {
		// TODO : ajouter evenement de remplissage
		return;
	}


	/* ######################### Affichage des robots ######################### */

	public static String repeat(int count, String with) {
		return new String(new char[count]).replace("\0", with);
	}

	@Override
	public String toString() {
		String res = new String();
		res += repeat(30, "-") + "\n";
		res += "CLASSE : " + getClass().getName() + "\n";
		res += "Position : " + this.position.toString() + "\n\n";
		res += "Vitesses :\n";
		for (NatureTerrain n : NatureTerrain.values()) {
			res += "   " + n.toString();
			res += repeat(21 - n.toString().length(), " ");
			res += this.vitesse.get(n) + "\n";
		}
		res += "\n";
		res += "Capacité réservoir      " + this.capaciteReservoir + "\n";
		res += "Temps de remplissage    " + this.tempsRemplissage + "\n";
		res += "Litres (unitaire)       " + this.litresUnitaire + "\n";
		res += "Temps (unitaire)        " + this.tempsUnitaire + "\n";
		res += "Distance de remplissage " + this.distanceRemplissage + "\n";
		res += repeat(30, "-") + "\n";
		return res;
	}
}
