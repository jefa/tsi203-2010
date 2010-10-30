package partuzabook.usuarioUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import partuzabook.datatypes.DatatypeNotification;
import partuzabook.datos.persistencia.beans.Notification;
import partuzabook.servicioDatos.exception.UserNotFoundException;
import partuzabook.servicioDatos.usuarios.ServicesUserRemote;
import partuzabook.serviciosNotificaciones.email.PartuzaMailer;

public class NotificationsBean {

	private static final String INPUT_OBLIG = "Campo obligatorio";

	//private ServicesNotificationRemote servicesNotif;
	private ServicesUserRemote servicesUser;

	private ArrayList<String> data;
	
	// General
	private boolean isUserLogged;
	private String toUser;
	private String toUserMessage;					
	private String subject;
	private String subjectMessage;
	private String body;
	private String bodyMessage;
	
	// Para el usuario logueado
	private String username;
	private String include = "includes/messageRcvList.xhtml";
	
	private List<DatatypeNotification> sentNotifications;
	private List<DatatypeNotification> recvNotifications;
		
	public NotificationsBean() {
		data = new ArrayList<String>();
		for (int i=0; i<20; i++)
			data.add("dato"+i);
	}

	private String getUsername() {
		if(this.username == null){
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
			this.username = (String) session.getAttribute("username");
		}
		return this.username;
	}

	private ServicesUserRemote getServicesUser(){
		if (servicesUser == null){
			try {
				Context ctx = getContext();
				this.servicesUser = (ServicesUserRemote) ctx.lookup("PartuzabookEAR/ServicesUser/remote");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return this.servicesUser;
	}

	/*private ServicesNotificationRemote getServicesNotification() {
		try {
			if (servicesNotif == null){
				Context ctx = getContext();
				this.servicesNotif = (ServicesNotificationRemote) ctx.lookup("PartuzabookEAR/ServicesNotification/remote");
			}
			return servicesNotif;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}*/

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
	
	public ArrayList<String> getData(){
		return data;
	}

	public List<DatatypeNotification> getNotificacionesEnviadas() {
		this.sentNotifications = getServicesUser().getUpdateNotificationsSent(getUsername());
		return this.sentNotifications;	
	}

	public void setNotificacionesEnviadas(ArrayList<DatatypeNotification> list) {
		this.sentNotifications = list;
	}
	
	public List<DatatypeNotification> getNotificacionesRecibidas() {
		this.recvNotifications = getServicesUser().getUpdateNotificationsReceived(getUsername());
		return this.recvNotifications;	
	}

	public void setNotificacionesRecibidas(ArrayList<DatatypeNotification> list) {
		this.recvNotifications = list;
	}
	
	public boolean getIsUserLogged(){
		this.isUserLogged = checkIfUserLogged();
		return this.isUserLogged;
	}
	
	public void setIsUserLogged(boolean logged){
		this.isUserLogged = logged;
	}
	
	public boolean checkIfUserLogged(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		String user = (String) session.getAttribute("username");		
		return (user != null );
	}
	
	public String getInclude() {
		return include;
	}

	public void setInclude(String include) {
		System.out.println("NotificationsBean.setInclude(): include now is="+include);
		this.include = include;
	}

	private void clearMessages() {
		setBodyMessage("");
		setSubjectMessage("");
		setToUserMessage("");
	}

	private boolean noMessages() {
		return ((toUserMessage == null || toUserMessage.equals("")) &&
				(subjectMessage == null || subjectMessage.equals("")) &&
				(bodyMessage == null || bodyMessage.equals("")));
	}
	
	public String sendMail(){
		//Limpiamos los mensajes
		clearMessages();
		if(toUser == null || toUser.equals(""))
			toUserMessage = INPUT_OBLIG;
		if(subject == null || subject.equals(""))
			subjectMessage = INPUT_OBLIG;
		if(body == null || body.equals(""))
			bodyMessage = INPUT_OBLIG;
		
		if(noMessages()) {
			try {
				
				DatatypeNotification notif = getServicesUser().createNotification(
					getUsername(), toUser, Notification.MAIL_NOTIF_TYPE, body);
				
				String emailTo = getServicesUser().getNormalUserMailAddress(toUser);
				
				if (PartuzaMailer.sendMail(null, emailTo, null, null, 
						subject, body, "text/plain")){
					return "okay";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "failure";
		} else {
			return "failure";
		}
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getToUserMessage() {
		return toUserMessage;
	}

	public void setToUserMessage(String toUserMessage) {
		this.toUserMessage = toUserMessage;
	}

	public String getSubjectMessage() {
		return subjectMessage;
	}

	public void setSubjectMessage(String subjectMessage) {
		this.subjectMessage = subjectMessage;
	}

	public String getBodyMessage() {
		return bodyMessage;
	}

	public void setBodyMessage(String bodyMessage) {
		this.bodyMessage = bodyMessage;
	}

}
