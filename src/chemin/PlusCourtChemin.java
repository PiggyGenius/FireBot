package chemin;

import java.util.EnumMap;
import simulation.Case;
import enumerations.NatureTerrain;

/** Classe abstraite de plus court chemin, les différents algorithmes héritent de cette classe */
public abstract class PlusCourtChemin {

	/** @return Chemin de source a destination
	 *  @param src Case source
	 *  @param dst Case destination
	 *  @param vitesse Mapping des vitesses associées aux terrains
	 **/
	public abstract Chemin getChemin(Case src,Case dst,EnumMap<NatureTerrain,Double> vitesse);

}
