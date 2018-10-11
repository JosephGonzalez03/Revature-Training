package model;

public class AwesomePerson extends Person {
	
	protected AwesomePerson() {
		System.out.println("I'm awesome.");
	}
	
	public void talk() {
		String out = "Name: " + name + "\nAge: " + age;
		System.out.println(out);
	}
}
