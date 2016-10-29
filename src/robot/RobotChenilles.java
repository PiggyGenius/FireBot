package robot;

import simulation.*;
import enumerations.*;
import java.lang.Double;

public class RobotChenilles extends Robot {

	public RobotChenilles(Coordonnee position) {
		super(position);
		System.out.println("OK");
		this.vitesse.put(NatureTerrain.EAU, new Double(60.0));
		this.vitesse.put(NatureTerrain.FORET, new Double(30.0));
		this.vitesse.put(NatureTerrain.ROCHE, new Double(60.0));
		this.vitesse.put(NatureTerrain.TERRAIN_LIBRE, new Double(60.0));
		this.vitesse.put(NatureTerrain.HABITAT, new Double(60.0));
	}

	@Override
	public double getVitesse(NatureTerrain terrain) {
		return this.vitesse.get(terrain);
	}

	@Override
	public void deverserEau(int volume) {
		return;
	}

	@Override
	public void remplirReservoir() {
		return;
	}
}
