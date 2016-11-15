package robot;

import simulation.*;
import enumerations.*;
import java.lang.Double;

public class RobotDrone extends Robot {

	/** Constructeur de RobotDrone
	 * @param position la position initiale
	 * @param vitesse la vitesse de deplacement */
	public RobotDrone(Case position, double vitesse) {

		super(position);

		double v = vitesse;
		if (vitesse <= 0.0) {
			v = 100.0;
		} else if (vitesse > 150.0) {
			v = 150.0;
		}

		v = this.convertVitesse(v);

		this.vitesse.put(NatureTerrain.EAU, new Double(v));
		this.vitesse.put(NatureTerrain.FORET, new Double(v));
		this.vitesse.put(NatureTerrain.ROCHE, new Double(v));
		this.vitesse.put(NatureTerrain.TERRAIN_LIBRE, new Double(v));
		this.vitesse.put(NatureTerrain.HABITAT, new Double(v));

		this.capaciteReservoir = 10000;
		this.qteReservoir = 10000;
		this.tempsRemplissage = 1800;
		this.litresUnitaire = 10000;
		this.tempsUnitaire = 30;
		this.distanceRemplissage = 0;
	}


	/** Constructeur de RobotDrone (en utilisant la vitesse par defaut)
	 * @param position la position initiale */
	public RobotDrone(Case position) {
		this(position, 100.0);
	}
}
