package partuzabook.servicioDatos.usuarios;
import java.util.List;

import javax.ejb.Remote;

import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeNotification;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.servicioDatos.exception.UserAlreadyExistsException;

@Remote
public interface ServicesUserRemote {

    /**
     * Registers a new account with id username
     * @throws UserAlreadyExistsException	- if id username is already taken
     * @param username						- New user's username for the account
     * @param password						- New user's password
     * @param mail							- New user's email
     * @return								- A datatype containing the new user's info
     */
	public DatatypeUser createNormalUser(String username, String password, String mail);
	
	/**
	 * Returns true if exists a normal user with id username
	 * @param username		- Id to search for user
	 */
	public boolean existsNormalUser(String username);
	
	/**
	 * Returns the password for the user with id username
	 * @param username		- Id for the user
	 * @return				- User's password
	 */
	public String getNormalUserPassword(String username);
	
	/**
	 * Returns a list of the latest X events the user is related to.
	 * @param username		- Id for the user
	 * @return				- List of events including a subset of its contents
	 */
    public List<DatatypeEventSummary> getEventSummaryByUser(String username);
    
    /**
     * Returns a list of the latest X update notifications for the user,
     * including read and unread 
     * @param username		- Id for the user
     * @return				- List of notifications for the user
     */
    public List<DatatypeNotification> getUpdateNotifications(String username);
    
}
