package partuzabook.usuarioUI;

import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import partuzabook.servicioDatos.eventos.ServicesEventRemote;
import partuzabook.servicioDatos.usuarios.ServicesUserRemote;
import partuzabook.serviciosUI.autenticacion.ServicesAutenticacionRemote;

public class LoginMB {

	private String text;
	private String userName;
	private String name;
	private String password;
	private boolean isUserLogged;
	
	public boolean getIsUserLogged(){
		this.userName = getUserName();
		this.isUserLogged = this.userName != null && this.userName != "";
		return this.isUserLogged;
	}

	public String getUserName() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		if (session != null)
			userName = SessionUtils.getUsername();
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getName(){
		if (this.name == null) {
			Context ctx;
			try {
				ctx = getContext();
				ServicesUserRemote serviceUser = (ServicesUserRemote) ctx.lookup("/PartuzabookEAR/ServicesUser/remote");
				if (serviceUser.existsNormalUser(this.userName)){
					this.name = serviceUser.getName(this.userName);
				}
			} catch (NamingException e) {
				e.printStackTrace();
			}
		} 
		return this.name;		
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String validUser() throws Exception {
		String returnString = "success";		
		Context ctx = getContext();
		ServicesAutenticacionRemote service = (ServicesAutenticacionRemote) ctx.lookup("PartuzabookEAR/ServicesAutenticacion/remote");	
		boolean usuarioValido = service.verifyUserAndPassword(userName, password);
				
		if (usuarioValido) {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext()
					.getSession(true);
			session.setAttribute("username", userName);
		} else {
			setText("User Name or Password is incorrect.");
			returnString = "failure";
		}

		return returnString;
	}

	public String logout() throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);		
		if (session != null) {
			session.invalidate();
			setText("Logout con exito.");
			return "logoutsuccess";
		}
		return "";

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

}
