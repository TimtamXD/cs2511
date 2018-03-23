/**
 * LAB02
 * @author z5115679
 * TODO: Implement the per day feature
 */

public class BankAccount {
	private int currentBalance;
	private int currentWithDrawn = 0;
	/**
	 * Constructor
	 */
	private BankAccount(int currentBalance, int currentWithDrawn) {
		super();
		this.currentBalance = currentBalance;
		this.currentWithDrawn = currentWithDrawn;
	}
	/**
	 * Getter & Setter Methods
	 */
	public int getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(int currentBalance) {
		this.currentBalance = currentBalance;
	}
	/**
	 * Withdraws cash
	 * @param withdrawalAmount
	 */
	public void withdraw(int withdrawalAmount) {
		if (this.currentWithDrawn + withdrawalAmount < 800) {
			this.currentBalance -= withdrawalAmount;
		} else {
			System.out.println("Error, you're trying to withdraw too much.");
		}
	}
	/**
	 * Deposits cash
	 * @param depositAmount
	 */
	public void deposit(int depositAmount) {
		this.currentBalance += depositAmount;
	}
}
