package chemin;

import java.util.EnumMap;
import simulation.Case;
import enumerations.NatureTerrain;

public abstract class PlusCourtChemin {

	public abstract Chemin getChemin(Case src,Case dst,EnumMap<NatureTerrain,Double> vitesse);
}
