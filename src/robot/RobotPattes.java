package robot;

import simulation.*;
import enumerations.*;
import java.lang.Double;

public class RobotPattes extends Robot {

	/** Constructeur de RobotPattes
	 * @param position la position initiale
	 * @param vitesse la vitesse de deplacement */
	public RobotPattes(Case position, double vitesse) {

		super(position);

		double v = vitesse;
		if (vitesse <= 0.0) {
			v = 30.0;
		}

		v = this.convertVitesse(v);

		this.vitesse.put(NatureTerrain.EAU, new Double(0.0));
		this.vitesse.put(NatureTerrain.FORET, new Double(v));
		this.vitesse.put(NatureTerrain.ROCHE, new Double(10.0));
		this.vitesse.put(NatureTerrain.TERRAIN_LIBRE, new Double(v));
		this.vitesse.put(NatureTerrain.HABITAT, new Double(v));

		this.capaciteReservoir = 0;
		this.qteReservoir = 0;
		this.tempsRemplissage = 0;
		this.litresUnitaire = 10;
		this.tempsUnitaire = 1;
		this.distanceRemplissage = 0;
	}


	/** Constructeur de RobotPattes (en utilisant la vitesse par defaut)
	 * @param position la position initiale */
	public RobotPattes(Case position) {
		this(position, 30.0);
	}
}
