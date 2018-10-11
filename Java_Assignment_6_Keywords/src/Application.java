import com.mycompany.main.*;

public class Application {
	public static void main(String[] args) {	
		float out = StaticClass.sum(1, 2);
		FinalClass mClass = new FinalClass();
		ConcreteClass cClass = new ConcreteClass();
		
		System.out.println("" + out);
		printFinal(mClass);
		cClass.run();
	}
	
	public static void printFinal(FinalClass o) {
		System.out.println("" + o.myConstant);
	}
}
