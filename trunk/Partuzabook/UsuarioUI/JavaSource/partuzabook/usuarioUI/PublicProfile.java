package partuzabook.usuarioUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.servicioDatos.usuarios.ServicesUserRemote;

public class PublicProfile {

	private static final String INPUT_OBLIG = "Campo obligatorio";
	private static final String PASS_NOT_EQUALS = "Las contraseñas no coinciden";
	private static final String BAD_EMAIL = "El email ingresado no es un email valido";
	private static final String SERVICES_USER_REMOTE = "PartuzabookEAR/ServicesUser/remote";
	
	private List<DatatypeEventSummary> eventosDelUsuario;
	private int cantEventosDelUsuario = -1;
	private DatatypeUser user;
	private String userId;
	
	private String newPassword;
	private String confirmNewPassword;

	private String nameMessage;
	private String newPasswordMessage;
	private String confirmNewPasswordMessage;
	private String emailMessage;
	private String message;
	
	public void setUserId(String userId) {
		System.out.println("Llame a setUserName");
		this.userId = userId;
		try {
			Context ctx = getContext();
			ServicesUserRemote servicesUser = (ServicesUserRemote)ctx.lookup("PartuzabookEAR/ServicesUser/remote");
			this.user = servicesUser.getUserForPublicProfile(userId);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			//TODO: Redirigir a página de error
			e.printStackTrace();
		}
	}
	
	public String getUserId() {
		return this.userId;
	}
	
	public void setEventosDelUsuario(List<DatatypeEventSummary> eventosDelUsuario) {
		this.eventosDelUsuario = eventosDelUsuario;
	}
	
	public List<DatatypeEventSummary> getEventosDelUsuario() {	
			try {
				Context ctx = getContext();
				ServicesUserRemote servicesUser = (ServicesUserRemote)ctx.lookup(SERVICES_USER_REMOTE);
				this.eventosDelUsuario = servicesUser.getEventSummaryByUser(userId);
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (Exception e) {
				//TODO: Redirigir a pagina de error
				e.printStackTrace();
				this.eventosDelUsuario = new ArrayList<DatatypeEventSummary>();
			}
		return this.eventosDelUsuario;
	}
	
	public void setCantEventosDelUsuario(int cantEventosDelUsuario) {
		this.cantEventosDelUsuario = cantEventosDelUsuario;
	}
	
	public int getCantEventosDelUsuario() {
		this.cantEventosDelUsuario = getEventosDelUsuario().size();
		return this.cantEventosDelUsuario;
	}
	
	public void setUser(DatatypeUser user) {
		this.user = user;
	}
	
	public DatatypeUser getUser() {
		return this.user;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getNewPassword() {
		return this.newPassword;
	}
	
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	
	public String getConfirmNewPassword() {
		return this.confirmNewPassword;
	}
	
	public void setNewPasswordMessage(String newPasswordMessage) {
		this.newPasswordMessage = newPasswordMessage;
	}
	
	public String getNewPasswordMessage() {
		return this.newPasswordMessage;
	}
	
	public void setConfirmNewPasswordMessage(String confirmNewPasswordMessage) {
		this.confirmNewPasswordMessage = confirmNewPasswordMessage;
	}
	
	public String getConfirmNewPasswordMessage() {
		return this.confirmNewPasswordMessage;
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
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
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
	
	public String enviarPM() {
		System.out.println("Llame a enviarPM");
		return "";
	}
	
	public String cambiarAvatar() {
		System.out.println("Llame a cambiarAvatar");
		return "";
	}
	
	private boolean noMessages() {
		return ((newPasswordMessage == null || newPasswordMessage.equals("")) &&
				(confirmNewPassword == null || confirmNewPassword.equals("")) &&
				(emailMessage == null || emailMessage.equals("")) &&
				(nameMessage == null || nameMessage.equals("")));
	}
	
	private void clearMessages() {
		setNewPasswordMessage("");
		setConfirmNewPasswordMessage("");
		setNameMessage("");
		setEmailMessage("");
		setMessage("");
	}
	
	private boolean validEmail(String email) {
		String regex = "[a-zA-Z0-9]+(\\.-\\w)*@[a-zA-Z0-9]+(\\.-\\w)*(\\.\\w{2,4})+";
		return email.matches(regex);
	}
	
	public String userModification() {
		
		//Limpiamos los mensajes
		clearMessages();
		if(user.name == null || user.name.equals(""))
			nameMessage = INPUT_OBLIG;
		if(newPassword == null || newPassword.equals(""))
			newPasswordMessage = INPUT_OBLIG;
		if(confirmNewPassword == null || confirmNewPassword.equals(""))
			confirmNewPasswordMessage = INPUT_OBLIG;
		if(user.email == null || user.email.equals(""))
			emailMessage = INPUT_OBLIG;
		
		
		try {
			Context ctx = getContext();
			ServicesUserRemote servicesUser = (ServicesUserRemote)ctx.lookup(SERVICES_USER_REMOTE);
			if(!noMessages()) {
				//Hubo errores, debo recuperar el user desde base
				user = servicesUser.getUserForPublicProfile(user.username);
			} else {
				
				if(!newPassword.equals(confirmNewPassword))
					confirmNewPassword = PASS_NOT_EQUALS;
				if(!validEmail(user.email))
					emailMessage = BAD_EMAIL;
				
				if(!noMessages()) {
					//Hubo errores, debo recuperar el user desde base
					user = servicesUser.getUserForPublicProfile(user.username);
				} else {
					DatatypeUser nu = servicesUser.updateNormalUser(user.username, newPassword, user.email, user.name, user.imagePath);
					user = nu;
					message = "Usuario modificado con éxito";
				}
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "success";
	}
}
