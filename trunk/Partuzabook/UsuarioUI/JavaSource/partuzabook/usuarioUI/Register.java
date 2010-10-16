package partuzabook.usuarioUI;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.servicioDatos.usuarios.ServicesUserRemote;

public class Register {

	private String username;
	private String password;
	private String passwordRepeated;
	private String name;
	private String email;
	
	private String message;
		
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
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public String userRegistration(){
		//Limpiamos los mensajes
		setMessage("");
		
		if(!password.equals(passwordRepeated)) {
			setMessage("Las contraseñas no son iguales.<br>");
		}
		if(!validEmail(email)) {
			setMessage(getMessage() + "El emal ingresado no es válido.<br>");
		}
		
		ServicesUserRemote servicesUser = null;
		try {
			Context ctx = getContext();
			servicesUser = (ServicesUserRemote)ctx.lookup("PartuzabookEAR/ServicesUser/remote");
			if(servicesUser.existsNormalUser(username)) {
				setMessage(getMessage() + "Ya existe el nombre de usuario.<br>");
			}
			
			if(!getMessage().equals("")) {
				//Hubo errores
				setPassword("");
				setPasswordRepeated("");
				return "failure";
			}
			
			servicesUser.createNormalUser(username, password, email, name);
			return "success";
			
		} catch (NamingException ne) {
			setMessage(getMessage() + "Error en la confirmación. Intente nuevamente.");
			setPassword("");
			setPasswordRepeated("");
			return "failure";
		} catch(Exception e) {
			//TODO: Reedirigir a una página de error
			setMessage(getMessage() + "Error en la confirmación. Intente nuevamente luego de unos minutos.");
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
