package robot;

import simulation.*;
import enumerations.*;
import java.lang.Double;

public class RobotRoues extends Robot {

	/** Constructeur de RobotRoues
	 * @param position la position initiale
	 * @param vitesse la vitesse de deplacement */
	public RobotRoues(Case position, double vitesse) {

		super(position);

		double v = vitesse;
		if (vitesse <= 0.0) {
			v = 80.0;
		}

		v = this.convertVitesse(v);

		this.vitesse.put(NatureTerrain.EAU, new Double(0.0));
		this.vitesse.put(NatureTerrain.FORET, new Double(0.0));
		this.vitesse.put(NatureTerrain.ROCHE, new Double(0.0));
		this.vitesse.put(NatureTerrain.TERRAIN_LIBRE, new Double(v));
		this.vitesse.put(NatureTerrain.HABITAT, new Double(v));

		this.capaciteReservoir = 5000;
		this.qteReservoir = 5000;
		this.tempsRemplissage = 600;
		this.litresUnitaire = 100;
		this.tempsUnitaire = 5;
		this.distanceRemplissage = 1;
	}


	/** Constructeur de RobotRoues (en utilisant la vitesse par defaut)
	 * @param position la position initiale */
	public RobotRoues(Case position) {
		this(position, 80.0);
	}
}
