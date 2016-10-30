package simulation.evenement;

public class EvenementDeplacement {

	private Robot robot;

	private Case dest;

	public EvenementDeplacement(Robot r, Case dest) {
		this.robot = Robot;
		this.dest = dest;
	}

	public void execute() {
		System.out.println("Robot : " + this.robot.toString()
			+ "Destination : " + this.dest.toString() + "\n");
	}
}
