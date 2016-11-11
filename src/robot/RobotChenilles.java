package robot;

import simulation.*;
import enumerations.*;
import java.lang.Double;

public class RobotChenilles extends Robot {

	public RobotChenilles(Case position, double vitesse) {
		
		super(position);

		double v = vitesse;
		if (vitesse <= 0.0) {
			v = 60.0;
		} else if (vitesse > 80.0) {
			v = 80.0;
		}

		this.vitesse.put(NatureTerrain.EAU, new Double(-1.0));
		this.vitesse.put(NatureTerrain.FORET, new Double(0.5*v));
		this.vitesse.put(NatureTerrain.ROCHE, new Double(-1.0));
		this.vitesse.put(NatureTerrain.TERRAIN_LIBRE, new Double(v));
		this.vitesse.put(NatureTerrain.HABITAT, new Double(v));

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
