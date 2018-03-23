/**
 * Tests
 * @author z5115679
 * 
 * Preconditions :things that must be true before method is called":
 * - People do money in terms of integers
 * - Bank accounts are created initially with 0 value
 * - Cannot deposit negative or null values (TEST 1)
 * - Cannot withdraw less than available (TEST 2)
 * 
 * Postconditions "things that must be true after method is complete":
 * - Withdrawal limits are not breached in both classes (TEST 3)
 * - Payment limits are not breached (TEST 4)
 * 
 * Invariants "things that must be always true":
 * - Account value can never be negative (TEST 5)
 * - Limits are never breached (TEST 6)
 * 
 * Assumptions for testing:
 * - There will be no tomorrow.
 * 
 * Covariance and contravariance ???
 */
public class Test {

	public static void main(String[] args) {
		BankAccount A = new BankAccount(0);
		InternetAccount B = new InternetAccount(0);
		
		// TEST 1
		System.out.println("TEST1:");
		// Testing depositing BankAccount 0:
		A.deposit(0);
		// Testing depositing BankAccount -1:
		A.deposit(-1);
		// Testing depositing InternetAccount 0:
		B.deposit(0);
		// Testing depositing InternetAccount -1:
		B.deposit(-1);
		assert (A.getCurrentBalance() == 0);
		assert (B.getCurrentBalance() == 0);
		System.out.println("Passed.");
		System.out.println("---------------------------------------");
		
		// TEST 2
		System.out.println("TEST2:");
		// Testing withdrawing BankAccount 1000:
		A.withdraw(1000);
		// Testing withdrawing InternetAccount 1000:
		B.withdraw(1000);
		assert (A.getCurrentBalance() == 0);
		assert (B.getCurrentBalance() == 0);
		System.out.println("Passed.");
		System.out.println("---------------------------------------");
		
		// TEST 3
		// ceebs
	}

}
