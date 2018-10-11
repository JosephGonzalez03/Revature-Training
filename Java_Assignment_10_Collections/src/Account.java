import java.util.ArrayList;
import java.util.List;

public class Account {
	public long id;
	public String accountType;

	public Account(long id, String accountType) {
		//super();
		this.id = id;
		this.accountType = accountType;
	}
	
	public static List<Account> createAccounts(int numAccounts) {
		List<Account> accList = new ArrayList<>();
		
		for(int i=0; i<numAccounts; i++) {
			Account acc = new Account(i + 1000, "savings");
			accList.add(acc);			
		}
		
		return accList;
	}
	
	@Override
	public String toString() {
		return "Account[id: " + this.id + "]"; 
	}
}
