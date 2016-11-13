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

	public int getQteReservoir() {
		return this.qteReservoir;
	}

	public void diminuerQteReservoir(int volume) {
		if (this.qteReservoir < volume) {
			throw new IllegalArgumentException("Quantité d'eau à déverser invalide.");
		}
		this.qteReservoir -= volume;
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

	public boolean isDispo() {
		return this.dispo;
	}

	/** 
	 * @param terrain
	 * 	La nature du terrain à traverser
	 * @return Vitesse du robot sur ce terrain */
	public double getVitesse(NatureTerrain terrain) {
		return this.vitesse.get(terrain);
	}

	public EnumMap<NatureTerrain,Double> getVitesseMap(){
		return this.vitesse;
	}


	/* ######################### Evenements  ######################### */

	public void planifierAction(Chemin chemin, Simulateur sim) {
		this.dispo = false;
		planifierDeplacement(chemin, sim);
		planifierDeversement(sim);
		planifierRemplissage(sim);
	}


	/** Planifie l'action de se deplacer
	 * Ajoute la liste des eveneemnts elementaires necessaires au deplacement */
	private void planifierDeplacement(Chemin c, Simulateur sim) {
		double tailleCase = (double)sim.getSimulation().getCarte().getTailleCases() / 10.0;
		for (Destination dest : c.getChemin()) {
			sim.ajouteEvenement(new EvenementDeplacement(dest.getTemps()*tailleCase 
								+ sim.getDateSimulation(), this, dest.getPosition()));
		}
	}


	/** Planifie l'action de deverser de l'eau sur la case courante */
	private void planifierDeversement(Simulateur sim) {
		// TODO : ajouter evenement de deversement
		// recuperer date
		//
		// tant que (incendie pas éteint et encore de l'eau)
		//     planifier deversement unitaire
		//     incrementer date
		return;
	}


	/** Action de remplir le reservoir */
	private void planifierRemplissage(Simulateur sim) {
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

	/** convertit une vitesse de km/h à m/s */
	public double convertVitesse(double vitesse) {
		return vitesse * 1000 / 3600;
	}
}
