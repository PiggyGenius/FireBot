package simulation;

import robot.Robot;

public class Matrice {
	
	Simulateur sim;
	int[][] matrice;

	public Matrice(Simulateur sim) {
		this.sim = sim;
		int nbRobot = sim.getSimulation().getNbRobots();
		int nbIncendie = sim.getSimulation().getNbIncendies();
		this.matrice = new int[nbRobot][nbIncendie];
	}

	public void init() {
		for (int i = 0; i < this.sim.getSimulation().getNbRobots(); i++) {
			for (int j = 0; j < this.sim.getSimulation()
					.getNbIncendies(); j++) {
				Robot robotActu = this.sim.getSimulation().getRobot(i);
				Incendie incendieActu = this.sim.getSimulation().getIncendie(j);
				if (robotActu.getCapaciteReservoir() > incendieActu
						.getIntensite()) {
					matrice[i][j] = (int) (this.sim.getSimulation()
							.getChemin(robotActu.getPosition(),
									incendieActu.getCase(),
									robotActu.getVitesseMap())
							.getTemps()
							+ ((float) incendieActu.getIntensite()
									/ (float) robotActu.getLitresUnitaire())
									* robotActu.getTempsUnitaire());
				} else {
					matrice[i][j] = (int) (this.sim.getSimulation()
							.getChemin(robotActu.getPosition(),
									incendieActu.getCase(),
									robotActu.getVitesseMap())
							.getTemps() + robotActu.getTempsUnitaire());
				}
			}
		}
	}

	private int minLigne(int i) {
		int minActu = Integer.MAX_VALUE;
		for (int j = 0; j < this.sim.getSimulation().getNbIncendies(); j++) {
			if (this.matrice[i][j] < minActu) {
				minActu = matrice[i][j];
			}
		}
		return minActu;
	}

	private void soustrLigne(int i, int val) {
		for (int j = 0; j < this.sim.getSimulation().getNbIncendies(); j++) {
			matrice[i][j] -= val;
		}
	}

	private void ordonnancementRealisable() {
		int min;
		for (int i = 0; i < this.sim.getSimulation().getNbRobots(); i++) {
			min = minLigne(i);
			this.soustrLigne(i, min);
		}
	}
}
