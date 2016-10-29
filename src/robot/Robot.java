package robot;

import java.lang.Double;
import java.util.EnumMap;
import java.util.Iterator;

import enumerations.*;
import simulation.*;

/** classe abstraite Robot */
public abstract class Robot {

	protected Case position;
	/* contient les vitesses du robot pour chaque élément (-1 si inaccessible) */
	protected EnumMap<NatureTerrain, Double> vitesse;
	protected int capaciteReservoir;
	protected int tempsRemplissage;
	protected int litresUnitaire;
	protected int tempsUnitaire;

	/** constructeur de la classe abstraite Robot
	 * @param position
	 * 	La position initiale du robot */
	public Robot(Coordonnee position) {
		this.setPosition(position);
		vitesse = new EnumMap<NatureTerrain, Double>(NatureTerrain.class);
	}


	/** @return Position courante */
	public Case getPosition() {
		return this.position;
	}


	/** @param position
	 * 	La nouvelle position du Robot */
	public void setPosition(Coordonnee position) {
		this.position = new Case(position, NatureTerrain.FORET); // TODO Recuperer la nature initiale du terrain
	}


	/** 
	 * @param terrain
	 * 	La nature du terrain à traverser
	 * @return Vitesse du robot sur ce terrain */
	public abstract double getVitesse(NatureTerrain terrain);


	/** Action de deverser de l'eau sur la case courante 
	 * @param volume
	 * 	le volume d'eau a deverser */
	public abstract void deverserEau(int volume);


	/** Action de remplir le reservoir */
	public abstract void remplirReservoir();


	@Override
	public String toString() {
		return new String("Position : " + this.position);
	}
}
