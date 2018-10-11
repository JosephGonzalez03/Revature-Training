
public class Application {
	public static void main(String[] args) {
		Runner rr = new Runner();
		
		try {
			rr.run();
		}
		catch(CustomException e) {
			e.printStackTrace();
		}
	}
}
