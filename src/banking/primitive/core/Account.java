package banking.primitive.core;

/**
 * Account.java
 * Abstract class for an Account to be used for Checking or Savings accounts
 * @author kevinagary
 * @since: 2/20/2016
 *
 */
public abstract class Account implements java.io.Serializable {
    private static final long _serialVersionUID = 1L;

    protected enum State {
        OPEN, CLOSED, OVERDRAWN
    };

    protected float _balance = 0.0F;
    protected String _name;
    private State _state;

    protected Account(String n) {
        _name = n;
        _state = State.OPEN;
    }

    protected Account(String n, float b) {
        this(n); 
        _balance = b;
    }

    /**
     * @return name of the Account
     */
    public final String getName() {
        return _name;
    }

    /**
     * @return balance in the Account
     */
    public final float getBalance() {
        return _balance;
    }

    /**
     * Adds money to an account. May not be done if the account is CLOSED
     * 
     * @param parameter
     *            amount is a deposit and must be > 0
     * @return true if the deposit was successful, false if not due to amount or
     *         invalid state
     */
    public abstract boolean deposit(float amount);

    /**
     * Takes money out of an account. If the balance falls below 0 then the
     * account is moved to an OVERDRAWN state
     * 
     * @param parameter
     *            amount is a withdrawal and must be > 0
     * @return true if the deposit was successful, false if not due to amount or
     *         invalid state
     */
    public abstract boolean withdraw(float amount);

    /**
     * @return either "Checking" or "Savings"
     */
    public abstract String getType();

    protected final State _getState() {
        return _state;
    }

    protected final void _setState(State s) {
        _state = s;
    }

    public String toString() {
        return "Account " + _name + " has $" + _balance + "and is " + _getState()
                + "\n";
    }
}
