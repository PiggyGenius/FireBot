package robot;

import simulation.*;
import enumerations.*;
import java.lang.Double;

public class RobotPattes extends Robot {

	public RobotPattes(Case position, double vitesse) {

		super(position);

		this.vitesse.put(NatureTerrain.EAU, new Double(0.0));
		this.vitesse.put(NatureTerrain.FORET, new Double(vitesse));
		this.vitesse.put(NatureTerrain.ROCHE, new Double(10.0));
		this.vitesse.put(NatureTerrain.TERRAIN_LIBRE, new Double(vitesse));
		this.vitesse.put(NatureTerrain.HABITAT, new Double(vitesse));

		this.capaciteReservoir = 0;
		this.tempsRemplissage = 0;
		this.litresUnitaire = 10;
		this.tempsUnitaire = 1;
		this.distanceRemplissage = 0;
	}


	public RobotPattes(Case position) {
		this(position, 30.0);
	}
}
