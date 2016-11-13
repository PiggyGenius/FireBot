import java.awt.Color;
import java.util.zip.DataFormatException;
import java.io.*;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import io.LecteurDonnees;
import simulation.*;
import chemin.*;
import enumerations.NatureTerrain;


public class TestCarteGUI {
    public static void main(String[] args) {
		if (args.length < 1) {
            System.out.println("Syntaxe: java TestLecteurDonnees <nomDeFichier>");
            System.exit(1);
        }
		
        try {
			DonneesSimulation simulation = LecteurDonnees.lire(args[0]);
	        GUISimulator gui = new GUISimulator(600, 600, Color.BLACK);
	        Simulateur simule_terrain = new Simulateur(gui,simulation);
			simulation.setPlusCourtChemin();

			ChefPompier chef = new ChefPompier(simulation);
			Chemin chemin = chef.getChemin(new Case(40,15,NatureTerrain.TERRAIN_LIBRE),chef.ChoisirRobot(new Incendie(new Case(5,5,NatureTerrain.TERRAIN_LIBRE),45)));
			if(chemin != null)
				simule_terrain.setPath(chemin.getChemin());
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }

    }
}
