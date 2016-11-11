package chemin;

/** On réalise une classe statique avec un constructeur privé et des méthodes statiques. */
public final class PlusCourtChemin {

	/** Constructeur privée pour éviter des instanciations */
	private PlusCourtChemin(){
	}

	/** Méthode statique qui calcule le plus court chemin par Dijkstra 
	 *  @param src Case de départ
	 *  @param dst Case de destination
	 **/
	public static Chemin Dijkstra(Case src,Case dst){
		List<Case> listeCase = new ArrayList<Case>();
	}

	/** 
	 * @param destination Case de destination
	 * @return Liste de Case pour se rendre à destination avec le temps du trajet
	 **/
	public Chemin getChemin(Case destination){
		return PlusCourtChemin.dijkstra(this.position,destination,this.vitesse);
	}
}
