package banking.primitive.core;

/**
 * Savings.java
 * Savings class that extends Account
 * @author kevinagary
 * @since: 2/20/2016
 *
 */
public class Savings extends Account {
	private static final long _serialVersionUID = 111L;
	private int _numWithdraws = 0;

	public Savings(String name) {
		super(name);
	}

	public Savings(String name, float balance) throws IllegalArgumentException {
		super(name, balance);
	}

	public String getType() { return "Checking"; }
	
	/**
	 * A deposit comes with a fee of 50 cents per deposit
	 */
	public boolean deposit(float amount) {
		if (_getState() != State.CLOSED && amount > 0.0f) {
			_balance = _balance + amount - 0.50F;
			if (_balance >= 0.0f) {
				_setState(State.OPEN);
			}
		}
		return false;
	}

	/**
	 * A withdrawal. After 3 withdrawals a fee of $1 is added to each withdrawal.
	 * An account whose balance dips below 0 is in an OVERDRAWN state
	 */
	public boolean withdraw(float amount) {
		if (_getState() == State.OPEN && amount > 0.0f) {
			_balance = _balance - amount;
			_numWithdraws++;
			if (_numWithdraws > 3)
				_balance = _balance - 1.0f;
			// KG BVA: should be < 0
<<<<<<< HEAD
			if (balance < 0.0f) {
				setState(State.OVERDRAWN);
=======
			if (_balance <= 0.0f) {
				_setState(State.OVERDRAWN);
>>>>>>> refs/remotes/origin/1206496664-4
			}
			return true;
		}
		return false;
	}

	public String getType() { return "Savings"; }

	public String toString() {
		return "Savings: " + getName() + ": " + getBalance();
	}
}
