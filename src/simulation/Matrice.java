package simulation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import chemin.Chemin;
import chemin.PlusCourtChemin;
import robot.Robot;

public class Matrice {
	
	int[][] matrice;

	public Matrice(int nbRobots, int nbIncendies) {
		this.matrice = new int[nbRobots][nbIncendies];
	}

public void init(ArrayList<Incendie> listeIncendies, ArrayList<Robot> listeRobots, Simulateur sim) {
		for (int i = 0; i < matrice.length; i++) {
			for (int j = 0; j < matrice[0].length; j++) {
				Robot robotActu = listeRobots.get(i);
				Incendie incendieActu = listeIncendies.get(j);
				if (robotActu.getCapaciteReservoir() > incendieActu
						.getIntensite()) {
					Chemin chemin = sim.getSimulation()
							.getChemin(robotActu.getPosition(),
									incendieActu.getCase(),
									robotActu.getVitesseMap());
					if(chemin == null) {
						matrice[i][j] = Integer.MAX_VALUE;
					}
					else {
						matrice[i][j] = (int) (chemin.getTemps()
								+ ((float) incendieActu.getIntensite()
										/ (float) robotActu.getLitresUnitaire())
										* robotActu.getTempsUnitaire());
					}
				} else {
					Chemin chemin = sim.getSimulation()
							.getChemin(robotActu.getPosition(),
									incendieActu.getCase(),
									robotActu.getVitesseMap());
					if(chemin == null) {
						matrice[i][j] = Integer.MAX_VALUE;
					}
					else {
						matrice[i][j] = (int) (sim.getSimulation()
								.getChemin(robotActu.getPosition(),
										incendieActu.getCase(),
										robotActu.getVitesseMap())
								.getTemps() + robotActu.getTempsUnitaire());
					}
					
				}
			}
		}
	}

	private int minLigne(int i) {
		int minActu = Integer.MAX_VALUE;
		for (int j = 0; j < matrice[0].length; j++) {
			if (this.matrice[i][j] < minActu) {
				minActu = matrice[i][j];
			}
		}
		return minActu;
	}

	private void soustrLigne(int i, int val) {
		for (int j = 0; j < matrice[0].length; j++) {
			matrice[i][j] -= val;
		}
	}

	public void ordonnancementRealisable() {
		int min;
		for (int i = 0; i < matrice.length; i++) {
			min = minLigne(i);
			this.soustrLigne(i, min);
		}
	}
	
	public int getZero(int ligne) {
		for(int j = 0; j < matrice[0].length; j++) {
			if(matrice[ligne][j] == 0) {
				return j;
			}
		}
		return -1; /* Par les transformations effectuées en amont */
	}			   /* on est sûr qu'il y a au moins 1 zero dans chaque ligne */
	
	
	public List<Integer> searchZero(int ligne) {
		List<Integer> l = new LinkedList<Integer>();
		for(int j = 0; j < matrice[0].length; j++) {
			if(matrice[ligne][j] == 0) {
				l.add(j);
			}
		}
		return l;
	}
	
	
}
