package chemin;

import simulation.Case;

/** On définit un trajet comme une position et un temps, la source n'est pas stockée */
public class Destination {
	private Case position;
	private double temps;

	/** Constructeur de Destination, la source n'est pas stockée
	 *  @param position Case de destination
	 *  @param temps Temps du trajet
	 **/
	public Destination(Case position,double temps){
		this.position = position;
		this.temps = temps;
	}

	/** @return Position de la case destination */
	public Case getPosition(){
		return this.position;
	}

	/** @return Temps de trajet depuis la source vers la destination */
	public double getTemps(){
		return this.temps;
	}
}
