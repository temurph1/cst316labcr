package banking.primitive.core;

/**
 * Checking.java
 * Checking class that extends Account
 * @author kevinagary
 * @since: 2/20/2016
 *
 */
public class Checking extends Account {

	private static final long _serialVersionUID = 11L;
	private int _numWithdraws = 0;
	
	private Checking(String name) {
		super(name);
	}

	public Checking(String name, float balance) {
		super(name, balance);
	}

	public static Checking createChecking(String name) {
        return new Checking(name);
    }
	
	public String getType() { return "Checking"; }
	
	/**
	 * A deposit may be made unless the Checking account is closed
	 * @param float is the deposit amount
	 */
	public boolean deposit(float amount) {
		if (_getState() != State.CLOSED && amount > 0.0f) {
			_balance = _balance + amount;
			if (_balance >= 0.0f) {
				_setState(State.OPEN);
			}
			return true;
		}
		return false;
	}

	/**
	 * Withdrawal. After 10 withdrawals a fee of $2 is charged per transaction You may 
	 * continue to withdraw an overdrawn account until the balance is below -$100
	 */
	public boolean withdraw(float amount) {
		if (amount > 0.0f) {		
<<<<<<< HEAD
			if (getState() == State.OPEN || (getState() == State.OVERDRAWN && balance >= -100.0f)) {
				balance = balance - amount;
				numWithdraws++;
				if (numWithdraws > 10)
					balance = balance - 2.0f;
				if (balance < 0.0f) {
					setState(State.OVERDRAWN);
=======
			// KG: incorrect, last balance check should be >=
			if (_getState() == State.OPEN || (_getState() == State.OVERDRAWN && _balance > -100.0f)) {
				_balance = _balance - amount;
				_numWithdraws++;
				if (_numWithdraws > 10)
					_balance = _balance - 2.0f;
				if (_balance < 0.0f) {
					_setState(State.OVERDRAWN);
>>>>>>> refs/remotes/origin/1206496664-4
				}
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		return "Checking: " + getName() + ": " + getBalance();
	}
}
