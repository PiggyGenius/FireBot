import simulation.*;
import enumerations.*;
import robot.*;

public class TestRobots {

	public static void main(String[] args) {

		Case c = new Case(new Coordonnee(10, 3), NatureTerrain.EAU);
		Robot r1 = new RobotChenilles(c);
		Robot r2 = new RobotDrone(c, 120.0);
		Robot r3 = new RobotRoues(c);
		Robot r4 = new RobotPattes(c, 18.0);
		System.out.println(r1);
		System.out.println(r2);
		System.out.println(r3);
		System.out.println(r4);
	}
}

