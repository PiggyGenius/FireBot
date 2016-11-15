package simulation;

import java.awt.Color;
import java.util.EnumMap;
import java.util.PriorityQueue;
import java.util.List;
import java.util.HashSet;
import java.util.Iterator;
import java.io.*;
import java.util.zip.DataFormatException;
import io.*;
import gui.GUISimulator;
import gui.Rectangle;
import simulation.*;
import gui.Simulable;
import gui.Text;
import enumerations.*;
import simulation.evenement.*;
import chemin.*;


public class Simulateur implements Simulable {
	private GUISimulator gui;
	private DonneesSimulation simulation;
	private int x_step;
	private int y_step;
	private ChefPompier chef;
	// TEMPORARY JUST TO TEST STUFF
	private HashSet<Destination> chemin;
	private long dateSimulation;
	private PriorityQueue<Evenement> listeEvenements = new PriorityQueue<Evenement> ();

	/** Constructeur du simulateur
	 * @param gui l'objet GUISimulator
	 * @param simulation les donnees de simulation */
	public Simulateur(GUISimulator gui,DonneesSimulation simulation){
		this.gui = gui;
		this.simulation = simulation;
		this.dateSimulation = 0;
		this.y_step = (int) Math.floor(this.gui.getPanelHeight() / this.simulation.getNbLignes());
		this.x_step = (int) Math.floor(this.gui.getPanelWidth() / this.simulation.getNbColonnes());
		gui.setSimulable(this);
		this.chef = null;
	}
	
	/** Methode d'increment du pas de temps et de gestion des evenements associes */
	@Override
	public void next(){
		this.incrementeDate();
		this.draw();
		this.chef.calculDeplacementExtinction();
	}

	/** Methode de reinitialisation de la simulation */
	@Override
	public void restart(){
		try {
			System.out.println("Clicked on restart"); // DEBUG
			this.listeEvenements.clear();
			this.dateSimulation = 0;

			this.simulation = LecteurDonnees.lire(this.simulation.getName());
			this.gui.reset();
			this.simulation.setPlusCourtChemin();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Ce fichier n'existe pas.");
		} catch (DataFormatException e) {
			throw new IllegalArgumentException("Fichier invalide.");
		}
		
	}

	/** @param chef le ChefPompier */
	public void setChef(ChefPompier chef) {
		this.chef = chef;
	}

	/** @return le ChefPompier */
	public ChefPompier getChef() {
		return this.chef;
	}


	/** Mise a jour de l'affichage */
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
		for(int i = 0; i < nbLignes; i++){
			for(int j = 0; j < nbColonnes; j++){
				couleur_case = couleur_terrain.get(this.simulation.getNatureTerrain(i,j));
				gui.addGraphicalElement(new Rectangle(this.x_step/2+this.x_step*j,this.y_step/2+this.y_step*i,couleur_case,couleur_case,this.x_step,this.y_step));
			}
		}

		/* On ajoute le feu !!! */
		couleur_case = Color.decode("#cc0000");
		Coordonnee c;
		for(int i = 0; i < this.simulation.getNbIncendies(); i++){
			c = this.simulation.getCoordonneeIncendie(i);
			gui.addGraphicalElement(new Rectangle(this.x_step/2+this.x_step*c.getColonne(),this.y_step/2+this.y_step*c.getLigne(),couleur_case,couleur_case,this.x_step,this.y_step));
		}

		/* Et enfin les robots */
		for(int i = 0; i < this.simulation.getNbRobots(); i++){
			c = this.simulation.getCoordonneeRobot(i);
			couleur_case = this.simulation.getCouleurRobot(i);
			gui.addGraphicalElement(new Rectangle(this.x_step/2+this.x_step*c.getColonne(),this.y_step/2+this.y_step*c.getLigne(),couleur_case,couleur_case,this.x_step,this.y_step));
			}
	}


	/** Ajout d'evenement
	 * @param e evenement a ajouter */
	public void ajouteEvenement(Evenement e) {
		listeEvenements.add(e);
	}

	/** Incremente la date courante et traite les evenements correspondants */
	public void incrementeDate() {
		this.dateSimulation ++;
		//System.out.println(this.dateSimulation); // DEBUG
		Evenement e;
		// TODO : on recherche 2 fois de suite le meilleur, c'est dommage
		while (! this.simulationTerminee() && listeEvenements.peek().getDate() <= this.dateSimulation) {
			e = listeEvenements.poll();
			e.execute();
		}
	}

	/** @return true si la simulation est terminee */
	public boolean simulationTerminee() {
		return listeEvenements.isEmpty();
	}


	/** @return date courante de la simulation */
	public long getDateSimulation() {
		return this.dateSimulation;
	}

	/** @return les donnees de simulation */
	public DonneesSimulation getSimulation() {
		return this.simulation;
	}
}
