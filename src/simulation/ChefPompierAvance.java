package simulation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import chemin.Chemin;
import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
import robot.Robot;

public class ChefPompierAvance {

	private Simulateur sim;

	private ArrayList<Incendie> listeIncendie;
	private ArrayList<Incendie> listeIncendieDispo;
	private ArrayList<Robot> listeRobotDispo;


	public ChefPompierAvance() {
		for (Incendie i : sim.getSimulation().getListeIncendie()) {
			this.listeIncendie.add(new Incendie(i));
			this.listeIncendieDispo.add(i);
		}
		for (Robot r : sim.getSimulation().getListeRobot()) {
			this.listeRobotDispo.add(r);
		}
	}
	
	public void traitement() {
		while (!listeIncendie.isEmpty()) {
			if(listeIncendieDispo.isEmpty()) {
				listeIncendieDispo = (ArrayList<Incendie>) listeIncendie.clone();
			}
			else if(!listeRobotDispo.isEmpty()) {
				Matrice matriceOptim = new Matrice(
						listeRobotDispo.size(),
						listeIncendieDispo.size());
				matriceOptim.init(listeIncendieDispo, listeRobotDispo, sim);
				matriceOptim.ordonnancementRealisable();
				for (int ligne = 0; ligne < matriceOptim.matrice.length; ligne++) {
					for (int i : matriceOptim.searchZero(ligne)) {
						affectRobotIncendie(listeRobotDispo, listeIncendieDispo, ligne, i);
					}
				}
			}
			actuIncendie(listeIncendieDispo);
			actuRobot(listeRobotDispo);
		}
	}
	
	private void actuRobot(ArrayList<Robot> liste) {
		for(int i = 0; i < liste.size(); i++) {
			if(!liste.get(i).isDispo()) {
				liste.remove(i);
			}
			if(sim.getSimulation().getListeRobot().get(i).isDispo()) {
				liste.add(sim.getSimulation().getListeRobot().get(i));
			}
		}		
	}

	private void actuIncendie(ArrayList<Incendie> liste) {
		for(int i = 0; i < liste.size(); i++) {
			if(liste.get(i).getEnExtinction()) {
				liste.remove(i);
			}
		}
	}

	private boolean robotDispo(ArrayList<Robot> listeRobots) {
		for(Robot rob : listeRobots) {
			if(rob.isDispo()) {
				return true;
			}
		}
		return false;
	}
	
	
	private boolean incendieDispo(boolean[] listeIncendiesAffect) {
		for(int i = 0; i < listeIncendiesAffect.length; i++) {
			if(!listeIncendiesAffect[i]) {
				return true;
			}
		}
		return false;
	}
	
	
	public void affectRobotIncendie(ArrayList<Robot> robot, ArrayList<Incendie> incendie, int indexRobot, int indexIncendie) {
		if(!robot.get(indexRobot).isDispo() || incendie.get(indexIncendie).getEnExtinction()) {
			return;
		}
		Chemin c = this.getChemin(incendie.get(indexIncendie).getCase(), robot.get(indexRobot));
		incendie.get(indexIncendie).setEnExtinction(true);
		robot.get(indexRobot).occuper();
		robot.get(indexRobot).planifierDeplacement(c, incendie, this, true); /* this est un ChefPompierAvance et non un ChefPompier */
	}
	
	/**
	 * @param dest Case de destination
	 * @param pompier Robot qui fera le déplacement
	 * @return Chemin pour se rendre à destination avec le temps du trajet
	 **/
	public Chemin getChemin(Case dest, Robot pompier) {
		return this.sim.getSimulation().getChemin(pompier.getPosition(), dest, pompier.getVitesseMap());
	}
}
