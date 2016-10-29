package simulation;

import java.awt.Color;
import gui.GUISimulator;
import gui.Rectangle;
import simulation.*;
import gui.Simulable;
import gui.Text;

public class Simulateur implements Simulable {
	private GUISimulator gui;
	private Simulateur simule_terrain;

	public Simulateur(GUISimulator gui,DonneesSimulation simulation){
		this.gui = gui;
		this.simule_terrain = simule_terrain;
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
		Color test_color = Color.decode("#f2ff28");
		/* EAU, FORET, ROCHE, TERRAIN_LIBRE, HABITAT */
		String[] terrain_s = {"#40a4df","#0A290A","#45463D","#66CD00","#663300"};
		Color[] terrain = new Color[5];
		for(int i = 0; i < 5; i++)
			terrain[i] = Color.decode(terrain_s[i]);
		gui.addGraphicalElement(new Rectangle(20,20,test_color,test_color,20));
	}
}
