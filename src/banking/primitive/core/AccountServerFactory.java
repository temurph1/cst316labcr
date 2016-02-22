package banking.primitive.core;

/**
 * AccountServerFactory.java
 * Singleton factory for returning active accounts
 * @author kevinagary
 * @since: 2/20/2016
 *
 */
public class AccountServerFactory {

	protected static AccountServerFactory singleton = null;

	protected AccountServerFactory() {

	}

	public static AccountServerFactory getMe() {
		if (singleton == null) {
			singleton = new AccountServerFactory();
		}

		return singleton;
	}

	public AccountServer lookup() {
		return new ServerSolution();
	}
}
