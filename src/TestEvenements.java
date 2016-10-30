import java.awt.Color;
import java.util.zip.DataFormatException;
import java.io.*;

import simulation.*;
import simulation.evenement.*;
import enumerations.*;
import robot.*;
import gui.GUISimulator;
import gui.Simulable;
import io.LecteurDonnees;


public class TestEvenements {
	
	public static void main(String[] args) {
		System.out.println("OK 0");

		try {
			DonneesSimulation simulation = LecteurDonnees.lire(args[0]);
			GUISimulator gui = new GUISimulator(600, 600, Color.BLACK);
			Simulateur s = new Simulateur(gui, simulation);
			
			Robot robert = simulation.getRobot(0);

			for (int i = 0; i < 10; i++) {
				Coordonnee c_src = simulation.getCoordonneeRobot(0);
				Case dst = simulation.getCarte().getCase(c_src.getLigne()+i, c_src.getColonne());
				System.out.println(dst);
				s.ajouteEvenement(new EvenementDeplacement(i, robert, dst));
			}

		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		} catch (DataFormatException e) {
			throw new IllegalArgumentException();
		}
	}
}
