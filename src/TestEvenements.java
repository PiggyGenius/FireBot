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
			
			Robot robert = simulation.getRobot(1);
			int temps = 3;

			// deplacement Nord
			s.ajouteEvenement(new EvenementDeplacement(temps, robert, simulation.getCarte().getVoisin(robert.getPosition(),Direction.NORD))); temps ++;
			// extinction
			s.ajouteEvenement(new EvenementDeversement(temps, robert, robert.getLitresUnitaire())); temps += robert.getTempsUnitaire();
			// deplacement ouest x2
			s.ajouteEvenement(new EvenementDeplacement(temps, robert, simulation.getCarte().getCase(5,4))); temps ++;
			s.ajouteEvenement(new EvenementDeplacement(temps, robert, simulation.getCarte().getCase(5,3))); temps ++;
			// remplissage (ça prend suuuuuuuper longtemps, 600 unités de temps)
			s.ajouteEvenement(new EvenementRemplissage(temps, robert)); temps += robert.getTempsRemplissage();
			// deplacement est x2
			s.ajouteEvenement(new EvenementDeplacement(temps, robert, simulation.getCarte().getCase(5,4))); temps ++;
			s.ajouteEvenement(new EvenementDeplacement(temps, robert, simulation.getCarte().getCase(5,5))); temps ++;
			// extinction
			s.ajouteEvenement(new EvenementDeversement(temps, robert, robert.getLitresUnitaire())); temps += robert.getTempsUnitaire();
			// retour à la case initiale
			s.ajouteEvenement(new EvenementDeplacement(temps, robert, simulation.getCarte().getVoisin(robert.getPosition(),Direction.SUD))); temps ++;


		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		} catch (DataFormatException e) {
			throw new IllegalArgumentException();
		}
	}
}
