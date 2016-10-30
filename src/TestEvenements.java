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

			Coordonnee c_src = new Coordonnee(12, 4);
			Coordonnee c_dst1 = new Coordonnee(1, 7);
			Coordonnee c_dst2 = new Coordonnee(2, 11);
			Coordonnee c_dst3 = new Coordonnee(3, 5);
			Coordonnee c_dst4 = new Coordonnee(4, 9);

			Case src = new Case(c_src, NatureTerrain.EAU);
			Case dst1 = new Case(c_dst1, NatureTerrain.FORET);
			Case dst2 = new Case(c_dst2, NatureTerrain.EAU);
			Case dst3 = new Case(c_dst3, NatureTerrain.ROCHE);
			Case dst4 = new Case(c_dst4, NatureTerrain.HABITAT);

			Robot r = new RobotChenilles(src);
			Evenement e1 = new EvenementDeplacement(8, r, dst3);
			Evenement e2 = new EvenementDeplacement(2, r, dst1);
			Evenement e3 = new EvenementDeplacement(5, r, dst2);
			Evenement e4 = new EvenementDeplacement(8, r, dst4);

			assert s.simulationTerminee() == true;
			s.ajouteEvenement(e1);
			assert s.simulationTerminee() == false;
			s.ajouteEvenement(e2);
			s.ajouteEvenement(e3);
			s.ajouteEvenement(e4);
			assert s.simulationTerminee() == false;

			for (int i = 0; i < 10; i++) {
				s.incrementeDate();
			}


		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		} catch (DataFormatException e) {
			throw new IllegalArgumentException();
		}
	}
}
