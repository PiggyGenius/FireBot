package io;

import java.io.*;
import java.util.*;
import java.util.zip.DataFormatException;
import simulation.*;
import enumerations.*;



/**
 * Lecteur de cartes au format spectifié dans le sujet.
 * Les données sur les cases, robots puis incendies sont lues dans le fichier,
 * puis simplement affichées.
 * A noter: pas de vérification sémantique sur les valeurs numériques lues.
 *
 * IMPORTANT:
 *
 * Cette classe ne fait que LIRE les infos et les afficher.
 * A vous de modifier ou d'ajouter des méthodes, inspirées de celles présentes
 * (ou non), qui CREENT les objets au moment adéquat pour construire une
 * instance de la classe DonneesSimulation à partir d'un fichier.
 *
 * Vous pouvez par exemple ajouter une méthode qui crée et retourne un objet
 * contenant toutes les données lues:
 *    public static DonneesSimulation creeDonnees(String fichierDonnees);
 * Et faire des méthode creeCase(), creeRobot(), ... qui lisent les données,
 * créent les objets adéquats et les ajoutent ds l'instance de
 * DonneesSimulation.
 */
public class LecteurDonnees {
	/* On ajoute un attribut DonnesSimulation qui sera éditer par les méthodes */
	private static DonneesSimulation simulation;

    /** Lit et affiche le contenu d'un fichier de donnees (cases, robots et incendies)
     *  @param fichierDonnees nom du fichier à lire
	 *  @throws FileNotFoundException le fichier n'existe pas ou ne peut etre ouvert
	 *  @throws DataFormatException format des données du fichier incorrect
	 *  @return donnees de simulation associées au fichier
     **/
    public static DonneesSimulation lire(String fichierDonnees) throws FileNotFoundException, DataFormatException {
		simulation = new DonneesSimulation();
		simulation.setName(fichierDonnees);
        System.out.println("\n == Lecture du fichier" + fichierDonnees);
        LecteurDonnees lecteur = new LecteurDonnees(fichierDonnees);
        lecteur.lireCarte();
        lecteur.lireIncendies();
        lecteur.lireRobots();
        scanner.close();
        System.out.println("\n == Lecture terminee");
		return simulation;
    }




    private static Scanner scanner;

    /** Constructeur prive; impossible d'instancier la classe depuis l'exterieur
     *  @param fichierDonnees nom du fichier a lire
	 *  @throws FileNotFoundException le fichier n'existe pas ou ne peut etre ouvert
     **/
    private LecteurDonnees(String fichierDonnees)
        throws FileNotFoundException {
        scanner = new Scanner(new File(fichierDonnees));
        scanner.useLocale(Locale.US);
    }

    /** Lit et affiche les donnees de la carte.
	 *  @throws DataFormatException format des données du fichier incorrect
     **/
    private void lireCarte() throws DataFormatException {
        ignorerCommentaires();
        try {
            int nbLignes = scanner.nextInt();
            int nbColonnes = scanner.nextInt();
            int tailleCases = scanner.nextInt();	// en m

			/* On créé la grille */
			//this.simulation = new DonneesSimulation();
			simulation.setCarte(nbLignes,nbColonnes,tailleCases);

            System.out.println("Carte " + nbLignes + "x" + nbColonnes
                    + "; taille des cases = " + tailleCases);

            for (int lig = 0; lig < nbLignes; lig++) {
                for (int col = 0; col < nbColonnes; col++) {
                    lireCase(lig, col);
                }
            }

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbLignes nbColonnes tailleCases");
        }
        // une ExceptionFormat levee depuis lireCase est remontee telle quelle
    }




    /** Lit et affiche les donnees d'une case
	 *  @param lig indice de ligne
	 *  @param col indice de colonne
	 *  @throws DataFormatException format des données du fichier incorrect
     **/
    private void lireCase(int lig, int col) throws DataFormatException {
		ignorerCommentaires();
		System.out.print("Case (" + lig + "," + col + "): ");
		String chaineNature = new String();

		try {
			chaineNature = scanner.next();
			System.out.println(chaineNature);	
			simulation.setCase(new Coordonnee(lig,col),NatureTerrain.valueOf(chaineNature));
			verifieLigneTerminee();

			System.out.print("nature = " + chaineNature);

		} catch (NoSuchElementException e) {
			throw new DataFormatException("format de case invalide. "
					+ "Attendu: nature altitude [valeur_specifique]");
		}

		System.out.println();
	}


    /** Lit et affiche les donnees des incendies
	 * @throws DataFormatException format des données du fichier incorrect
     **/
    private void lireIncendies() throws DataFormatException {
        ignorerCommentaires();
        try {
            int nbIncendies = scanner.nextInt();
            System.out.println("Nb d'incendies = " + nbIncendies);
            for (int i = 0; i < nbIncendies; i++) {
                lireIncendie(i);
            }

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbIncendies");
        }
    }


    /** Lit et affiche les donnees du i-eme incendie
     * @param i indice de l'incendie
	 * @throws DataFormatException format des données du fichier incorrect
     **/
    private void lireIncendie(int i) throws DataFormatException {
        ignorerCommentaires();
        System.out.print("Incendie " + i + ": ");

        try {
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
            int intensite = scanner.nextInt();
			this.simulation.addIncendie(new Coordonnee(lig,col),intensite);
            if (intensite <= 0) {
                throw new DataFormatException("incendie " + i
                        + "nb litres pour eteindre doit etre > 0");
            }
            verifieLigneTerminee();

            System.out.println("position = (" + lig + "," + col
                    + ");\t intensite = " + intensite);

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format d'incendie invalide. "
                    + "Attendu: ligne colonne intensite");
        }
    }


    /** Lit et affiche les donnees des robots
	 *  @throws DataFormatException format des données du fichier incorrect
     **/
    private void lireRobots() throws DataFormatException {
        ignorerCommentaires();
        try {
            int nbRobots = scanner.nextInt();
            System.out.println("Nb de robots = " + nbRobots);
            for (int i = 0; i < nbRobots; i++) {
                lireRobot(i);
            }

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbRobots");
        }
    }


    /** Lit et affiche les donnees du i-eme robot
     * @param i indice du robot
	 * @throws DataFormatException format des données du fichier incorrect
     **/
    private void lireRobot(int i) throws DataFormatException {
        ignorerCommentaires();
        System.out.print("Robot " + i + ": ");

        try {
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
            System.out.print("position = (" + lig + "," + col + ");");
            String type = scanner.next();

            System.out.print("\t type = " + type);


            // lecture eventuelle d'une vitesse du robot (entier)
            System.out.print("; \t vitesse = ");
            String s = scanner.findInLine("(\\d+)");	// 1 or more digit(s) ?
            // pour lire un flottant:    ("(\\d+(\\.\\d+)?)");

			Coordonnee c = new Coordonnee(lig,col);
            if (s == null) {
                System.out.print("valeur par defaut");
				this.simulation.addRobot(c,TypeRobot.valueOf(type));
            } else {
                int vitesse = Integer.parseInt(s);
                System.out.print(vitesse);
				this.simulation.addRobot(c,TypeRobot.valueOf(type),vitesse);
            }
            verifieLigneTerminee();

            System.out.println();

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format de robot invalide. "
                    + "Attendu: ligne colonne type [valeur_specifique]");
        }
    }




    /** Ignore toute (fin de) ligne commencant par '#' */
    private void ignorerCommentaires() {
        while(scanner.hasNext("#.*")) {
            scanner.nextLine();
        }
    }

    /** Verifie qu'il n'y a plus rien a lire sur cette ligne (int ou float).
     *  @throws DataFormatException format des données du fichier incorrect
     **/
    private void verifieLigneTerminee() throws DataFormatException {
        if (scanner.findInLine("(\\d+)") != null) {
            throw new DataFormatException("format invalide, donnees en trop.");
        }
    }
}
