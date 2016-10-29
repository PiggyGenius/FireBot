package simulation;

public class Incendie {
	private Case position;
	private int intensite;

	public Incendie(Case case_terrain,int intensite){
		position = case_terrain;
		this.intensite = intensite;
	}
}
