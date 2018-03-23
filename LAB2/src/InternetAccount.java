/**
 * Sub Class of BankAccount: InternetAccount
 * @author z5115679
 * TODO: Implement month feature for payLimits
 */
public class InternetAccount extends BankAccount {
	private static int payLimit = 10;
	private static int noOfPayments = 0;
	/**
	 * Constructor
	 * @param currentBalance
	 */
	public InternetAccount(int currentBalance) {
		super(currentBalance);
	}
	/**
	 * Method to make payments
	 * @param paymentAmount
	 */
	public void pay (int paymentAmount) {
		if (paymentAmount >= this.getCurrentBalance()) {
			System.out.println ("Error. Insufficient funds.");
		} else if (noOfPayments >= payLimit) {
			System.out.println ("Error. Too many payments.");
		} else {
			int newBalance = this.getCurrentBalance();
			newBalance -= paymentAmount;
			this.setCurrentBalance(newBalance);
			noOfPayments++;
			this.checkWithdrawalLimit();
			this.currentWithDrawn++;
			System.out.printf ("Your current balance is: %d\n", this.getCurrentBalance());
		}
	}
	private void checkWithdrawalLimit() {
		if (this.currentWithDrawn >= this.withdrawLimit) {
			System.out.println ("Error. You have made too many withdrawals.");
		}
	}
}
