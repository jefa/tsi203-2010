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
import partuzabook.servicioDatos.notificaciones.ServicesNotificationRemote;
import partuzabook.servicioDatos.usuarios.ServicesUserRemote;
import partuzabook.serviciosNotificaciones.email.PartuzaMailer;

public class NotificationsBean {

	private static final String INPUT_OBLIG = "Campo obligatorio";
	private static final Integer PAGE_SIZE = new Integer(5);
	private static final String SERV_NOTIFICATION = "PartuzabookEAR/ServicesNotification/remote";
	private static final String SERV_USER = "PartuzabookEAR/ServicesUser/remote";

	private Integer page;

	private PartuzaMailer mailer = new PartuzaMailer();
	private ServicesNotificationRemote serviceNotification;
	private ServicesUserRemote serviceUser;
	
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
	private int sentNotificationsPage = 1;
	private int maxSentNotificationsPage = 1;
	private List<DatatypeNotification> recvNotifications;
	private int recvNotificationsPage = 1;
	private int maxRecvNotificationsPage = 1;
	private int recvUnread;
	private int recvAll;
	private List<DatatypeNotification> gralNotifications;
	private int gralNotificationsPage = 1;
	private int maxGralNotificationsPage = 1;
	private int gralUnread;
	private int gralAll;
	private List<DatatypeNotification> notifActive;
	
	private String notId;
	
	public NotificationsBean() {
	}

	private String getUsername() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);
		return SessionUtils.getUsername();
	}

	private ServicesNotificationRemote getServicesNotification() {
		if (serviceNotification == null) {
			try {
				Context ctx = getContext();
				this.serviceNotification = (ServicesNotificationRemote) ctx.lookup(SERV_NOTIFICATION);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return this.serviceNotification;
	}
	
	private ServicesUserRemote getServicesUser() {
		if (serviceUser == null) {
			try {
				Context ctx = getContext();
				this.serviceUser = (ServicesUserRemote) ctx.lookup(SERV_USER);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return this.serviceUser;
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
		this.sentNotifications = getServicesNotification().getUpdateNotificationsSent(
				getUsername());
		
		int from = (sentNotificationsPage - 1) * PAGE_SIZE;
		int to = from + PAGE_SIZE;
		if(this.sentNotifications.size() < to)
			to = this.sentNotifications.size();
		return this.sentNotifications.subList(from, to);
	}

	public void setNotificacionesEnviadas(ArrayList<DatatypeNotification> list) {
		this.sentNotifications = list;
	}

	public List<DatatypeNotification> getNotificacionesRecibidas() {
		
		recvNotifications = new ArrayList<DatatypeNotification>();
		recvUnread = 0;
		
		List<DatatypeNotification> aux = getServicesNotification().getUpdateNotificationsReceived(getUsername());
		for(Iterator<DatatypeNotification> it = aux.iterator(); it.hasNext(); ) {
			DatatypeNotification actual = it.next();
			if(actual.getType() == Notification.MAIL_NOTIF_TYPE) {
				recvNotifications.add(actual);
				if(!actual.getRead())
					recvUnread++;
			}
		}
		
		int from = (recvNotificationsPage - 1) * PAGE_SIZE;
		int to = from + PAGE_SIZE;
		if(this.recvNotifications.size() < to)
			to = this.recvNotifications.size();
		return this.recvNotifications.subList(from, to);
	}

	public void setNotificacionesRecibidas(ArrayList<DatatypeNotification> list) {
		this.recvNotifications = list;
	}

	public List<DatatypeNotification> getNotificacionesGeneral() {
		gralNotifications = new ArrayList<DatatypeNotification>();
		gralUnread = 0;
		List<DatatypeNotification> aux = getServicesNotification().getUpdateNotificationsReceived(getUsername());
		for(Iterator<DatatypeNotification> it = aux.iterator(); it.hasNext(); ) {
			DatatypeNotification actual = it.next();
			if(actual.getType() != Notification.MAIL_NOTIF_TYPE) {
				gralNotifications.add(actual);
				if(!actual.getRead())
					gralUnread++;
			}
		}
		
		int from = (gralNotificationsPage - 1) * PAGE_SIZE;
		
		int to = from + PAGE_SIZE;
		if(this.gralNotifications.size() < to)
			to = this.gralNotifications.size();
		return this.gralNotifications.subList(from, to);
		
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
		this.subject = "";
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

				DatatypeNotification notif = getServicesNotification().createNotification(getUsername(), toUser, Notification.MAIL_NOTIF_TYPE, body, subject);

				/*
				
				String emailTo = getServicesUser().getNormalUserMailAddress(toUser);
				if (mailer.sendFormattedMail(notif.userFrom, getServicesUser().getName(notif.userFrom), notif.text,
						notif.formattedDate, null, emailTo, null, null, subject)) {
					clearAllMessages();
					return "okay";
				} else {
					//TODO: Aclarar que no se pudo enviar una copia al mail. (se tiene que enviar una copia al mail??)
					System.out.println("No se pudo enviar el mail");
					clearAllMessages();					
				}
				*/
				clearAllMessages();				
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
		//this.page = this.notifActive.size() % PAGE_SIZE;
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

	public void setSentNotificationsPage(int sentNotificationsPage) {
		this.sentNotificationsPage = sentNotificationsPage;
	}

	public int getSentNotificationsPage() {
		return sentNotificationsPage;
	}

	public void setRecvNotificationsPage(int recvNotificationsPage) {
		this.recvNotificationsPage = recvNotificationsPage;
	}

	public int getRecvNotificationsPage() {
		return recvNotificationsPage;
	}

	public void setGralNotificationsPage(int gralNotificationsPage) {
		this.gralNotificationsPage = gralNotificationsPage;
	}

	public int getGralNotificationsPage() {
		return gralNotificationsPage;
	}
	
	public void sentNotificationNextPage() {
		if(sentNotificationsPage < maxSentNotificationsPage)
			sentNotificationsPage += 1;
	}
	
	public void sentNotificationPreviousPage() {
		if(sentNotificationsPage > 1)
			sentNotificationsPage -= 1;
	}
	
	public void recvNotificationsNextPage() {
		
		if(recvNotificationsPage < maxRecvNotificationsPage)
			recvNotificationsPage += 1;
	}
	
	public void recvNotificationsPreviousPage() {
		if(recvNotificationsPage > 1)
			recvNotificationsPage -= 1;
	}
	
	public void gralNotificationsNextPage() {
		if(gralNotificationsPage < maxGralNotificationsPage)
			gralNotificationsPage += 1;
	}
	
	public void gralNotificationsPreviousPage() {
		if(gralNotificationsPage > 1)
			gralNotificationsPage -= 1;
	}

	public void setMaxSentNotificationsPage(int maxSentNotificationsPage) {
		this.maxSentNotificationsPage = maxSentNotificationsPage;
	}

	public int getMaxSentNotificationsPage() {
		if(sentNotifications == null)
			getNotificacionesEnviadas();
		maxSentNotificationsPage = sentNotifications.size() / PAGE_SIZE;
		if(sentNotifications.size() % PAGE_SIZE > 0 || sentNotifications.size() == 0)
			maxSentNotificationsPage ++;
		return maxSentNotificationsPage;
	}

	public void setMaxRecvNotificationsPage(int maxRecvNotificationsPage) {
		this.maxRecvNotificationsPage = maxRecvNotificationsPage;
	}

	public int getMaxRecvNotificationsPage() {
		if(recvNotifications == null)
			getNotificacionesRecibidas();
		maxRecvNotificationsPage = recvNotifications.size() / PAGE_SIZE;
		if(recvNotifications.size() % PAGE_SIZE > 0 || recvNotifications.size() == 0)
			maxRecvNotificationsPage ++;
		return maxRecvNotificationsPage;
	}

	public void setMaxGralNotificationsPage(int maxGralNotificationsPage) {
		this.maxGralNotificationsPage = maxGralNotificationsPage;
	}

	public int getMaxGralNotificationsPage() {
		if(gralNotifications == null)
			getNotificacionesGeneral();
		int maxGralNotificationsPage = gralNotifications.size() / PAGE_SIZE;
		if(gralNotifications.size() % PAGE_SIZE > 0 || gralNotifications.size() == 0)
			maxGralNotificationsPage++;
		return maxGralNotificationsPage;
	}
	
	public void notLeida() {
		this.getServicesNotification().setNotificationRead(Integer.parseInt(notId), true);
	}

	public void setNotId(String notId) {
		this.notId = notId;
	}

	public String getNotId() {
		return notId;
	}

	public void setRecvUnread(int recvUnread) {
		this.recvUnread = recvUnread;
	}

	public int getRecvUnread() {
		return recvUnread;
	}

	public void setGralUnread(int gralUnread) {
		this.gralUnread = gralUnread;
	}

	public int getGralUnread() {
		return gralUnread;
	}

	public void setRecvAll(int recvAll) {
		this.recvAll = recvAll;
	}

	public int getRecvAll() {
		getNotificacionesRecibidas();
		return recvNotifications.size();
	}

	public void setGralAll(int gralAll) {
		this.gralAll = gralAll;
	}

	public int getGralAll() {
		getNotificacionesGeneral();
		return gralNotifications.size();
	}
}