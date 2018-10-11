package game;

public class Game {
	public static void main(String[] args) {
		GameObject p1 = new Player();
		GameObject e1 = new Enemy();
		
		p1.update();
		e1.update();
	}
}
