/**
 * Super Class: BankAccount
 * @author z5115679
 * TODO: Implement the day feature for withdrawalLimit
 */
public class BankAccount {
	private int currentBalance;
	public int currentWithDrawn = 0;
	public int withdrawLimit = 800;
	/**
	 * Constructor
	 * @param currentBalance
	 */
	public BankAccount (int currentBalance) {
		this.currentBalance = currentBalance;
	}
	/**
	 * Gets currentBalance
	 * @return currentBalance
	 */
	public int getCurrentBalance () {
		return currentBalance;
	}
	/**
	 * Sets currentBalance
	 * @param currentBalance
	 */
	public void setCurrentBalance (int currentBalance) {
		this.currentBalance = currentBalance;
	}
	/**
	 * Withdraws cash
	 * @param withdrawalAmount
	 */
	public void withdraw (int withdrawalAmount) {
		if ((currentWithDrawn + withdrawalAmount < withdrawLimit) && (withdrawalAmount >= this.currentBalance)) {
			this.currentBalance -= withdrawalAmount;
			currentWithDrawn += withdrawalAmount;
			System.out.printf ("Your current balance is: %d\n", this.currentBalance);
		} else {
			System.out.println ("Error, you're trying to withdraw too much.");
		}
	}
	/**
	 * Deposits cash
	 * @param depositAmount
	 */
	public void deposit (int depositAmount) {
		if (depositAmount <= 0) {
			System.out.println ("Error, you must deposit a positive value > 0.");
		} else {
			this.currentBalance += depositAmount;
			System.out.printf ("Your current balance is: %d\n", this.currentBalance);
		}
	}
}
