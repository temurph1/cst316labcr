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

	/** Empty constructor for style issues
 	*/
	protected AccountServerFactory() {

	}

	/** Gets the singleton variable
	* @return A singleton AccountServerFactory, instantiates a new ASF if singleton is set to null
	*/
	public static AccountServerFactory getMe() {
		if (singleton == null) {
			singleton = new AccountServerFactory();
		}

		return singleton;
	}

	/** Gets the server solution
	* @return new server solution object
	*/
	public AccountServer lookup() {
		return new ServerSolution();
	}
}
