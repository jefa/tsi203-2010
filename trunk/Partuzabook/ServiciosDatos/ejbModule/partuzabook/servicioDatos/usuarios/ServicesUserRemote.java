package partuzabook.servicioDatos.usuarios;
import java.util.List;

import javax.ejb.Remote;

import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeMostTagged;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.datos.persistencia.beans.NormalUser;
import partuzabook.servicioDatos.exception.UserAlreadyExistsException;
import partuzabook.servicioDatos.exception.UserNotFoundException;

@Remote
public interface ServicesUserRemote {

    /**
     * Registers a new account with id username
     * @throws UserAlreadyExistsException	- if id username is already taken
     * @param username						- New user's username for the account
     * @param password						- New user's password
     * @param mail							- New user's email
     * @param name							- New user's full name
     * @return								- A datatype containing the new user's info
     */
	public DatatypeUser createNormalUser(String username, String password, String mail, String name) throws UserAlreadyExistsException;
	
	public DatatypeUser createNormalUser(String username, String password, String mail, String name,long facebookId) throws UserAlreadyExistsException;
	
	/**
     * Registers a new Admin account with id username
     * @throws UserAlreadyExistsException	- if id username is already taken
     * @param username						- New user's username for the account
     * @param password						- New user's password
     * @param mail							- New user's email
     * @param name							- New user's full name
     * @return								- A datatype containing the new user's info
     */
	public DatatypeUser createAdminUser(String username, String password, String mail, String name) throws UserAlreadyExistsException;
	
	/**
     * Updates an user account with id username
     * @throws UserNotFoundException		- if id username doesn't exist
     * @param username						- User's username for the account
     * @param password						- User new password
     * @param mail							- User new email
     * @param name							- User new full name
     */
	public DatatypeUser updateNormalUser(String username, String password, String mail, String name) throws UserNotFoundException;
	
	/**
     * Updates an user account with id username
     * @throws UserNotFoundException		- if id username doesn't exist
     * @param username						- User's username for the account
     * @param password						- User new password
     * @param mail							- User new email
     * @param name							- User new full name
     */
	public DatatypeUser updateUserAvatar(String username, byte[] data, String mime) throws UserNotFoundException;
	
	/**
	 * Returns true if exists a normal user with id username
	 * @param username		- Id to search for user
	 */
	public boolean existsNormalUser(String username);
	
	/**
	 * Returns the name of the normal user with id username
	 * @param username		- Id to search for user
	 */
	public String getName(String username);
	
	/**
	 * Returns true if exists user with facebookid
	 * @param facebookid - Facebook Id's to search for user
	 */
	public boolean existsFacebookUser(long facebookid);
	

	/**
	 * Returns true if all normal users with username id belonging to usernames exists
	 * @param usernames		- Id's to search for user
	 */
	public List<Boolean> existsNormalUser(List<String> usernames);
	
	/**
	 * Returns the password for the user with id username
	 * @param username		- Id for the user
	 * @return				- User's password
	 */
	public String getNormalUserPassword(String username);
	
	/**
	 * Returns true if the users's password is password
	 * @param username		- Id for the user
	 * @param password		- password decripted to compare
	 * @return				- true if the password is ok
	 */
	public boolean isNormalUserPassword(String username, String password);
	
	/**
	 * Returns true if the admin's password is password
	 * @param username		- Id for the user
	 * @param password		- password decripted to compare
	 * @return				- true if the password is ok
	 */
	public boolean isAdminPassword(String username, String password);
	
	public NormalUser getNormalUserByFacebookId(long facebookId);
	
	/**
	 * Returns the password for the user with id username
	 * @param username		- Id for the user
	 * @return				- User's email address
	 */
	public String getNormalUserMailAddress(String username);
	
	/**
	 * Returns true if exists an admin user with id username
	 * @param username		- Id to search for user
	 */
	public boolean existsAdminUser(String username);
	
	/**
	 * Returns the password for the admin with id username
	 * @param username		- Id for the user
	 * @return				- User's password
	 */
	public String getAdminUserPassword(String username);
	
	/**
	 * Returns a list of the latest X events the user is related to.
	 * @param username		- Id for the user
	 * @return				- List of events including a subset of its contents
	 */
    public List<DatatypeEventSummary> getEventSummaryByUser(String username);
    
    /**
     * Returns a user by from his username.
     * @param username		- Id for the user
     * @return				- A datatype containing the user's info
     */
    public DatatypeUser getUserForPublicProfile(String username);
    
    /**
     * Return the user image
     * @param username		- Id for the user
     * @return				- The image avatar of the specified user
     * */
    public byte[] getUserAvatar(String username, String thumbnail);
    
    /**
     * Returns all the NormalUsers in the server
     * @return				- All the normal users in the server
     * */
    public List<DatatypeUser> findAllNormalUsers();
	
	/**
	 * Returns  a list of the best qualified pictures in all the server
	 * @param lenght - length of the list to return
	 */
	public List<DatatypeMostTagged> getMostTagged(int lenght);

	/**
	 * Returns the data for the public content
	 * @param type
	 * @param pos
	 * @param thumbnail
	 * @return
	 */
	public byte[] getPublicAvatar(String type, int pos, String thumbnail);
}
