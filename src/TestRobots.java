import simulation.*;
import robot.*;

public class TestRobots {

	public static void main(String[] args) {

		Coordonnee c = new Coordonnee(10, 3);
		Robot r = new RobotChenilles(c);
		System.out.println(r);
	}
}

