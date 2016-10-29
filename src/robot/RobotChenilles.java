package robot;

import simulation.*;
import enumerations.*;
import java.lang.Double;

public class RobotChenilles extends Robot {

	public RobotChenilles(Case position, double vitesse) {

		super(position);

		this.vitesse.put(NatureTerrain.EAU, new Double(vitesse));
		this.vitesse.put(NatureTerrain.FORET, new Double(0.5*vitesse));
		this.vitesse.put(NatureTerrain.ROCHE, new Double(vitesse));
		this.vitesse.put(NatureTerrain.TERRAIN_LIBRE, new Double(vitesse));
		this.vitesse.put(NatureTerrain.HABITAT, new Double(vitesse));

		this.capaciteReservoir = 2000;
		this.tempsRemplissage = 300;
		this.litresUnitaire = 100;
		this.tempsUnitaire = 8;
		this.distanceRemplissage = 1;
	}


	public RobotChenilles(Case position) {
		this(position, 60.0);
	}
}
