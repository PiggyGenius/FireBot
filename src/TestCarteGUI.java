import java.awt.Color;
import java.util.zip.DataFormatException;
import java.io.*;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import io.LecteurDonnees;
import simulation.*;


public class TestCarteGUI {
    public static void main(String[] args) {
		if (args.length < 1) {
            System.out.println("Syntaxe: java TestLecteurDonnees <nomDeFichier>");
            System.exit(1);
        }
		
        try {
			DonneesSimulation simulation = LecteurDonnees.lire(args[0]);

	        GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
	        Simulateur simule_terrain = new Simulateur(gui,simulation);
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }

    }
}
