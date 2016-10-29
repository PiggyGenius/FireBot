import java.util.List;
import java.util.ArrayList;

public class DonneesSimulation {
	private Carte carte_terrain;
	private List<Incendie> liste_incendie = new ArrayList<Incendie>();
	private List<Robot> liste_robot = new ArrayList<Robot>();

	public DonneesSimulation(){
	}

	public void setCarte(int nbLignes,int nbColonnes,int tailleCases){
		this.carte_terrain = new Carte(nbLignes,nbColonnes,tailleCases);
	}

}
