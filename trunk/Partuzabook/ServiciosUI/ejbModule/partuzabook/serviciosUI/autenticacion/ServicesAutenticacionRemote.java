package partuzabook.serviciosUI.autenticacion;
import javax.ejb.Remote;

@Remote
public interface ServicesAutenticacionRemote {
	
	/**
     * Returns true if a user exists and the password is correct
     * @param username				- Username
     * @param password	- Password
     */ 
	public boolean verifyUserAndPassword(String username, String password) ;
	
	/**
     * Returns true if an admin exists and the password is correct
     * @param username				- Username
     * @param password	- Password
     */ 
	public boolean verifyAdminUserAndPassword(String username, String password);
}
