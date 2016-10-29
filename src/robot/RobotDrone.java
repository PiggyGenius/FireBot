package robot;

import simulation.*;
import enumerations.*;
import java.lang.Double;

public class RobotDrone extends Robot {

	public RobotDrone(Case position, double vitesse) {

		super(position);

		this.vitesse.put(NatureTerrain.EAU, new Double(vitesse));
		this.vitesse.put(NatureTerrain.FORET, new Double(vitesse));
		this.vitesse.put(NatureTerrain.ROCHE, new Double(vitesse));
		this.vitesse.put(NatureTerrain.TERRAIN_LIBRE, new Double(vitesse));
		this.vitesse.put(NatureTerrain.HABITAT, new Double(vitesse));

		this.capaciteReservoir = 10000;
		this.tempsRemplissage = 1800;
		this.litresUnitaire = 10000;
		this.tempsUnitaire = 30;
		this.distanceRemplissage = 0;
	}
}
