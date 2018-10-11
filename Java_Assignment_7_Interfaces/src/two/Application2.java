package two;

public class Application2 {
	public static void main(String[] args) {
		Animatable animatedThing = new MoverAndAnimate();
		
		animatedThing.animate();
		animatedThing.move();
	}
}
