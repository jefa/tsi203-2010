package partuzabook.usuarioUI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import partuzabook.datatypes.DatatypeNotification;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.datos.persistencia.beans.Notification;
import partuzabook.servicioDatos.usuarios.ServicesUserRemote;
import partuzabook.serviciosNotificaciones.email.PartuzaMailer;

public class NotificationsBean {

	private static final String INPUT_OBLIG = "Campo obligatorio";
	private static final Integer PAGE_SIZE = new Integer(5);

	private Integer page;

	private PartuzaMailer mailer = new PartuzaMailer();
	private ServicesUserRemote servicesUser;

	private List<DatatypeUser> results;
	private List<DatatypeUser> users;
	private String suggest = "";

	// General
	private boolean isUserLogged;
	private String toUser;
	private String toUserMessage;
	private String body;
	private String bodyMessage;
	private String subject;

	private String include = "includes/messageCompose.xhtml";

	private List<DatatypeNotification> sentNotifications;
	private List<DatatypeNotification> recvNotifications;
	private List<DatatypeNotification> gralNotifications;
	private List<DatatypeNotification> notifActive;

	public NotificationsBean() {
	}

	private String getUsername() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);
		return SessionUtils.getUsername();
	}

	private ServicesUserRemote getServicesUser() {
		if (servicesUser == null) {
			try {
				Context ctx = getContext();
				this.servicesUser = (ServicesUserRemote) ctx
						.lookup("PartuzabookEAR/ServicesUser/remote");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return this.servicesUser;
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

	public List<DatatypeNotification> getNotificacionesEnviadas() {
		this.sentNotifications = getServicesUser().getUpdateNotificationsSent(
				getUsername());
		return this.sentNotifications;
	}

	public void setNotificacionesEnviadas(ArrayList<DatatypeNotification> list) {
		this.sentNotifications = list;
	}

	public List<DatatypeNotification> getNotificacionesRecibidas() {
		
		recvNotifications = new ArrayList<DatatypeNotification>();
			
		List<DatatypeNotification> aux = getServicesUser().getUpdateNotificationsReceived(getUsername());
		for(Iterator<DatatypeNotification> it = aux.iterator(); it.hasNext(); ) {
			DatatypeNotification actual = it.next();
			if(actual.getType() == Notification.MAIL_NOTIF_TYPE)
				recvNotifications.add(actual);
		}
		return recvNotifications;
	}

	public void setNotificacionesRecibidas(ArrayList<DatatypeNotification> list) {
		this.recvNotifications = list;
	}

	public List<DatatypeNotification> getNotificacionesGeneral() {
		gralNotifications = new ArrayList<DatatypeNotification>();
		
		List<DatatypeNotification> aux = getServicesUser().getUpdateNotificationsReceived(getUsername());
		for(Iterator<DatatypeNotification> it = aux.iterator(); it.hasNext(); ) {
			DatatypeNotification actual = it.next();
			if(actual.getType() != Notification.MAIL_NOTIF_TYPE)
				gralNotifications.add(actual);
		}
		return gralNotifications;
	}

	public void setNotificacionesGeneral(ArrayList<DatatypeNotification> list) {
		this.gralNotifications = list;
	}

	public boolean getIsUserLogged() {
		this.isUserLogged = checkIfUserLogged();
		return this.isUserLogged;
	}

	public void setIsUserLogged(boolean logged) {
		this.isUserLogged = logged;
	}

	public boolean checkIfUserLogged() {
		String user = SessionUtils.getUsername();
		return (user != null);
	}

	public String getInclude() {
		return include;
	}

	public void setInclude(String include) {
		System.out.println("NotificationsBean.setInclude(): include now is="
				+ include);
		this.include = include;
	}

	private void clearMessages() {
		setBodyMessage("");
		setToUserMessage("");
	}

	private void clearAllMessages() {
		clearMessages();
		this.toUser = "";
		this.body = "";
	}

	private boolean noMessages() {
		return ((toUserMessage == null || toUserMessage.equals("")) && (bodyMessage == null || bodyMessage
				.equals("")));
	}

	public String sendMail() {
		// Limpiamos los mensajes
		clearMessages();
		if (toUser == null || toUser.equals(""))
			toUserMessage = INPUT_OBLIG;
		if (body == null || body.equals(""))
			bodyMessage = INPUT_OBLIG;

		if (noMessages()) {
			try {

				DatatypeNotification notif = getServicesUser()
						.createNotification(getUsername(), toUser,
								Notification.MAIL_NOTIF_TYPE, body, subject);

				String emailTo = getServicesUser().getNormalUserMailAddress(
						toUser);
				if (mailer.sendFormattedMail(notif.userFrom, getServicesUser().getName(notif.userFrom), notif.text,
						notif.formattedDate, null, emailTo, null, null, subject)) {
					clearAllMessages();
					return "okay";
				} else {
					//TODO: Aclarar que no se pudo enviar una copia al mail. (se tiene que enviar una copia al mail??)
					System.out.println("No se pudo enviar el mail");
					clearAllMessages();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "failure";
		} else {
			return "failure";
		}
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

	public String getBodyMessage() {
		return bodyMessage;
	}

	public void setBodyMessage(String bodyMessage) {
		this.bodyMessage = bodyMessage;
	}

	public List<DatatypeNotification> getNotifActive() {
		return notifActive;
	}

	public void setNotifActive(List<DatatypeNotification> notifActive) {
		this.notifActive = notifActive;
	}

	private List<DatatypeUser> getUsers() {
		if (this.users == null) {
			this.users = getServicesUser().findAllNormalUsers();
		}
		return this.users;
	}

	public List<DatatypeUser> autocomplete(Object suggestParam) {
		suggest = ((String) suggestParam).toLowerCase();
		results = new ArrayList<DatatypeUser>();
		Iterator<DatatypeUser> it = getUsers().iterator();
		while (it.hasNext()) {
			DatatypeUser user = (DatatypeUser) it.next();
			if (user.username.toLowerCase().contains(suggest)
					|| user.name.toLowerCase().contains(suggest)) {
				results.add(user);
			}
		}
		return results;
	}

	public Integer getPage() {
		this.page = this.notifActive.size() % PAGE_SIZE;
		return this.page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}
}