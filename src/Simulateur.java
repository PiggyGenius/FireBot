import java.awt.Color;
import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import gui.Text;

public class Simulateur implements Simulable {
	private GUISimulator gui;

	public Simulateur(GUISimulator gui){
		this.gui = gui;
		gui.setSimulable(this);
	}
	
	@Override
	public void next(){
		System.out.println("Clicked on next");
		this.draw();
	}

	@Override
	public void restart(){
		System.out.println("Clicked on restart");
		this.draw();
	}

	private void draw(){
		this.gui.reset();
		Color test_color = Color.decode("#f2ff28");
		gui.addGraphicalElement(new Rectangle(20,20,test_color,test_color,20));
	}
}
