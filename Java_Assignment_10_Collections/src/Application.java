import java.util.List;

public class Application {
	public static void main(String[] args) {
		List<Account> aList = Account.createAccounts(5);
		
		for(Account i : aList) {
			System.out.println("" + i.id);
		}
	}
}
