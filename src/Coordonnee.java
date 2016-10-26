public class Coordonnee {
	private int x;
	private int y;

	public Coordonnee(int x,int y){
		this.x = x;
		this.y = y;
	}

	public Coordonnee(Coordonnee c){
		this.setLigne(c.getLigne());
		this.setColonne(c.getColonne());
	}

	public int getLigne(){
		return this.x;
	}

	public int getColonne(){
		return this.y;
	}

	public void setLigne(int x){
		if(x < 0)
			throw new IllegalArgumentException();
		this.x = x;
	}

	public void setColonne(int y){
		if(y < 0)
			throw new IllegalArgumentException();
		this.y = y;
	}

	@Override
	public String toString(){
		return "("+this.x+";"+this.y+")";
	}
}
