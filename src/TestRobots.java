import simulation.*;
import enumerations.*;
import robot.*;

public class TestRobots {

	public static void main(String[] args) {

		Case c = new Case(new Coordonnee(10, 3), NatureTerrain.EAU);
		Robot r1 = new RobotChenilles(c, 60.0);
		Robot r2 = new RobotDrone(c, 100.0);
		Robot r3 = new RobotRoues(c, 80.0);
		Robot r4 = new RobotPattes(c, 30.0);
		System.out.println(r1);
		System.out.println(r2);
		System.out.println(r3);
		System.out.println(r4);
	}
}

