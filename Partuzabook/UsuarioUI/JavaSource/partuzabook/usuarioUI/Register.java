package partuzabook.usuarioUI;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.servicioDatos.usuarios.ServicesUserRemote;

public class Register {

	private static final String INPUT_OBLIG = "Campo obligatorio";
	private static final String PASS_NOT_EQUALS = "Las contraseñas no coinciden";
	private static final String USERNAME_ALREADY_EXISTS = "El nombre de usuario ya existe";
	private static final String BAD_EMAIL = "El email ingresado no es un email valido";
	
	private String username;
	private String password;
	private String passwordRepeated;
	private String name;
	private String email;
	
	private String usernameMessage;
	private String passwordMessage;
	private String passwordRepeatedMessage;
	private String nameMessage;
	private String emailMessage;
		
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPasswordRepeated(String passwordRepeated) {
		this.passwordRepeated = passwordRepeated;
	} 
	
	public String getPasswordRepeated() {
		return this.passwordRepeated;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setUsernameMessage(String usernameMessage) {
		this.usernameMessage = usernameMessage;
	}
	
	public String getUsernameMessage() {
		return this.usernameMessage;
	}
	
	public void setPasswordMessage(String passwordMessage) {
		this.passwordMessage = passwordMessage;
	}
	
	public String getPasswordMessage() {
		return this.passwordMessage;
	}
	
	public void setPasswordRepeatedMessage(String passwordRepeatedMessage) {
		this.passwordRepeatedMessage = passwordRepeatedMessage;
	}
	
	public String getPasswordRepeatedMessage() {
		return this.passwordRepeatedMessage;
	}
	
	public void setNameMessage(String nameMessage) {
		this.nameMessage = nameMessage;
	}
	
	public String getNameMessage() {
		return this.nameMessage;
	}
	
	public void setEmailMessage(String emailMessage) {
		this.emailMessage = emailMessage;
	}
	
	public String getEmailMessage() {
		return this.emailMessage;
	}
	
	private void clearMessages() {
		setUsernameMessage("");
		setPasswordMessage("");
		setPasswordRepeatedMessage("");
		setNameMessage("");
		setEmailMessage("");
	}
	
	private boolean noMessages() {
		return ((usernameMessage == null || usernameMessage.equals("")) &&
				(passwordMessage == null || passwordMessage.equals("")) &&
				(passwordRepeatedMessage == null || passwordRepeatedMessage.equals("")) &&
				(nameMessage == null || nameMessage.equals("")) &&
				(emailMessage == null || emailMessage.equals("")));
	}
	
	
	public String userRegistration(){
		//Limpiamos los mensajes
		clearMessages();
		if(username == null || username.equals(""))
			usernameMessage = INPUT_OBLIG;
		if(password == null || password.equals(""))
			passwordMessage = INPUT_OBLIG;
		if(passwordRepeated == null || passwordRepeated.equals(""))
			passwordRepeatedMessage = INPUT_OBLIG;
		if(name == null || name.equals(""))
			nameMessage = INPUT_OBLIG;
		if(email== null || email.equals(""))
			emailMessage = INPUT_OBLIG;
		
		if(noMessages()) {
			if(!password.equals(passwordRepeated)) {
				setPasswordRepeatedMessage(PASS_NOT_EQUALS);
			}
			if(!validEmail(email)) {
				setEmailMessage(BAD_EMAIL);
			}
			
			ServicesUserRemote servicesUser = null;
			try {
				Context ctx = getContext();
				servicesUser = (ServicesUserRemote)ctx.lookup("PartuzabookEAR/ServicesUser/remote");
				if(servicesUser.existsNormalUser(username) || servicesUser.existsAdminUser(username)) {
					setUsernameMessage(USERNAME_ALREADY_EXISTS);
				}
				
				if(!noMessages()) {
					//Hubo errores
					setPassword("");
					setPasswordRepeated("");
					return "failure";
				}
				
				servicesUser.createNormalUser(username, password, email, name);
				return "success";
				
			} catch (NamingException ne) {
				//TODO: Redirigir a página de error
				ne.printStackTrace();
				setPassword("");
				setPasswordRepeated("");
				return "failure";
			} catch(Exception e) {
				//TODO: Reedirigir a una página de error
				e.printStackTrace();
				setPassword("");
				setPasswordRepeated("");
				return "failure";
			}
		} else {
			//Hubo errores
			setPassword("");
			setPasswordRepeated("");
			return "failure";
		}
	}
	
	private Context getContext() throws NamingException {
		Properties properties = new Properties();
		properties.put("java.naming.factory.initial",
				"org.jnp.interfaces.NamingContextFactory");
		properties.put("java.naming.factory.url.pkgs",
				"org.jboss.naming rg.jnp.interfaces");
		properties.put("java.naming.provider.url", "jnp://localhost:1099");
		Context ctx = new InitialContext(properties);
		return ctx;
	}
	
	private boolean validEmail(String email) {
		String regex = "[a-zA-Z0-9]+(\\.-\\w)*@[a-zA-Z0-9]+(\\.-\\w)*(\\.\\w{2,4})+";
		return email.matches(regex);
	}
}
