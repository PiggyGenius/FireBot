package robot;

import java.lang.Double;
import java.util.EnumMap;
import java.util.Iterator;

import enumerations.*;
import simulation.*;
import simulation.evenement.*;
import chemin.*;

/** classe abstraite Robot */
public abstract class Robot {

	/* ######################### Attributs ######################### */

	/* position courante du robot */
	protected Case position;

	/* vitesses du robot pour chaque élément en m/s (0 si inaccessible) */
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
		this.dispo = true;
	}

	/* ######################### Getters / Setters ######################### */

	/** @return Position courante */
	public Case getPosition() {
		return this.position;
	}

	/** @return Coordonnees courantes */
	public Coordonnee getCoordonnee(){
		return this.position.getCoordonnee();
	}

	/** @param position	La nouvelle position du Robot */
	public void setPosition(Case position) {
		this.position = position;
	}

	/** @return capacite du reservoir */
	public int getCapaciteReservoir() {
		return this.capaciteReservoir;
	}

	/** @return la quantite d'eau dans le reservoir */
	public int getQteReservoir() {
		return this.qteReservoir;
	}

	/** @param volume la quantite d'eau a retirer
	 * @throws IllegalArgumentException si il reste moins que 'volume' d'eau dans le reservoir */
	public void diminuerQteReservoir(int volume) {
		if (this.capaciteReservoir == 0) {
			return;
		}
		if (this.qteReservoir < volume) {
			throw new IllegalArgumentException("Quantité d'eau à déverser invalide.");
		}
		this.qteReservoir -= volume;
	}

	/** @return temps de remplissage */
	public int getTempsRemplissage() {
		return this.tempsRemplissage;
	}

	/** @return nombre de litres deverses par temps de deversement */
	public int getLitresUnitaire() {
		return this.litresUnitaire;
	}

	/** @return temps unitaire de deversement */
	public int getTempsUnitaire() {
		return this.tempsUnitaire;
	}

	/** @return distance a laquelle doit se trouver le robot d'un point d'eau pour se remplir */
	public int getDistanceRemplissage() {
		return this.distanceRemplissage;
	}

	/** @return la disponibilite du robot */
	public boolean isDispo() {
		return this.dispo;
	}

	/** libere le robot */
	public void liberer() {
		this.dispo = true;
	}

	/** occupe (reserve) le robot */
	public void occuper() {
		this.dispo = false;
	}

	/** remplit le reservoir du robot */
	public void remplirReservoir() {
		this.qteReservoir = this.capaciteReservoir;
	}

	/** 
	 * @param terrain
	 * 	La nature du terrain à traverser
	 * @return Vitesse du robot sur ce terrain */
	public double getVitesse(NatureTerrain terrain) {
		return this.vitesse.get(terrain);
	}

	/** @return EnumMap des vitesses en fonction du terrain */
	public EnumMap<NatureTerrain,Double> getVitesseMap(){
		return this.vitesse;
	}


	/* ######################### Evenements  ######################### */


	/** Planifie l'action de se deplacer
	 * @param c le chemin pour aller à la destination
	 * @param incendie l'incendie (null si c'est pour un remplissage)
	 * @param chef le chef pompier qui a donne l'ordre
	 * @param extinction true si c'est pour une extinction de feu */
	public void planifierDeplacement(Chemin c, Incendie incendie, ChefPompier chef, boolean extinction) {
		this.dispo = false;
		double tailleCase = (double)chef.getSimulateur().getSimulation().
			getCarte().getTailleCases() / 10.0;

		// pour chaque case du chemin
		for (Destination dest : c.getChemin()) {
			// ajouter evenement de deplacement
			chef.getSimulateur().ajouteEvenement(new EvenementDeplacement(
						dest.getTemps()*tailleCase + chef.getSimulateur().
						getDateSimulation(), chef, this, dest.getPosition()));
		}
		// ajouter à la fin un evenement de fin de deplacement
		chef.getSimulateur().ajouteEvenement(new EvenementDeplacementFin(
					c.getTemps()*tailleCase + chef.getSimulateur().
					getDateSimulation(), chef, this, incendie, extinction));
	}



	/* ######################### Affichage des robots ######################### */

	private String repeat(int count, String with) {
		return new String(new char[count]).replace("\0", with);
	}

	/** @return l'affichage des caracteristiques d'un robot */
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

	/** convertit une vitesse de km/h à m/s 
	 * @param vitesse la vitesse en km/h a convertir 
	 * @return la vitesse convertie en m/s */

	public double convertVitesse(double vitesse) {
		return vitesse * 1000 / 3600;
	}
}
