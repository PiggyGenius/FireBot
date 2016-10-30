package simulation;

import java.awt.Color;
import java.util.EnumMap;
import gui.GUISimulator;
import gui.Rectangle;
import simulation.*;
import gui.Simulable;
import gui.Text;
import enumerations.*;
import simulation.evenement.*;


public class Simulateur implements Simulable {
	private GUISimulator gui;
	private DonneesSimulation simulation;

	private long dateSimulation;

	public Simulateur(GUISimulator gui,DonneesSimulation simulation){
		this.gui = gui;
		this.simulation = simulation;
		this.dateSimulation = 0;
		gui.setSimulable(this);
	}
	
	@Override
	public void next(){
		System.out.println("Clicked on next");
		this.draw();
	}

	@Override
	public void restart(){
		System.out.println("Clicked on restart");
		this.draw();
	}

	private void draw(){
		this.gui.reset();
		Color couleur_case;
		NatureTerrain nature_terrain;
		/* EAU, FORET, ROCHE, TERRAIN_LIBRE, HABITAT */
		String[] terrain_s = {"#40a4df","#0A290A","#45463D","#66CD00","#663300"};
		EnumMap<NatureTerrain, Color> couleur_terrain = new EnumMap<NatureTerrain, Color>(NatureTerrain.class);
		for(int i = 0; i < 5; i++)
			couleur_terrain.put(NatureTerrain.values()[i],Color.decode(terrain_s[i]));

		/* On affiche le terrain de la carte */
		int nbLignes = this.simulation.getNbLignes();
		int nbColonnes = this.simulation.getNbColonnes();	
		int x_step = (int) Math.floor(this.gui.getPanelHeight() / nbLignes);
		int y_step = (int) Math.floor(this.gui.getPanelWidth() / nbColonnes);
		for(int i = 0; i < nbLignes; i++){
			for(int j = 0; j < nbColonnes; j++){
				couleur_case = couleur_terrain.get(this.simulation.getNatureTerrain(i,j));
				gui.addGraphicalElement(new Rectangle(x_step*i,y_step*j,couleur_case,couleur_case,y_step));
			}
		}

		/* On ajoute le feu !!! */
		couleur_case = Color.decode("#cc0000");
		Coordonnee c;
		for(int i = 0; i < this.simulation.getNbIncendies(); i++){
			c = this.simulation.getCoordonneeIncendie(i);
			gui.addGraphicalElement(new Rectangle(x_step*c.getLigne(),y_step*c.getColonne(),couleur_case,couleur_case,y_step));
		}

		/* Et enfin les robots */
		couleur_case = Color.decode("#c0c0c0");
		for(int i = 0; i < this.simulation.getNbRobots(); i++){
			c = this.simulation.getCoordonneeRobot(i);
			gui.addGraphicalElement(new Rectangle(x_step*c.getLigne(),y_step*c.getColonne(),couleur_case,couleur_case,y_step));
		}

	}


	public void ajouteEvenement(Evenement e) {
		return;
	}

	private void incrementeDate() {
		this.dateSimulation ++;
	}

	private boolean simulationTerminee() {
		return false;
	}
}
