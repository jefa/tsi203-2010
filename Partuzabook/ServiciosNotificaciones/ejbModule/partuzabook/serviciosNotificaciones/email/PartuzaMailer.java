package partuzabook.serviciosNotificaciones.email;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import partuzabook.utils.StringUtils;

public class PartuzaMailer {
	
	private InputStream inMailTemplateStream;
	private String htmlMailText;
	private Session session;

	public InputStream getHTMLTemplateStream(){
		if (this.inMailTemplateStream == null){
			this.inMailTemplateStream = FacesContext.getCurrentInstance().getExternalContext().
				getResourceAsStream("/templates/mailTemplate.html");
		}
		return this.inMailTemplateStream;
	}
	
	public String getHTMLMailTemplate(){
		if (this.htmlMailText == null){
			try {
				this.htmlMailText = StringUtils.convertStreamToString(getHTMLTemplateStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return this.htmlMailText;
	}
	
	public boolean sendFormattedMail(String userName, String fullName, String message, String formattedDate, 
			String from, String to, String cc, String bcc, String subject) {

		String htmlBody = new String(getHTMLMailTemplate());
		//htmlBody.replaceAll("userid", notif.userFrom);
		StringUtils.replace(htmlBody, "userid", userName);
		//htmlBody.replaceAll("contentfeederURL", "/UserImageFeeder?username="+this.username+"&#38;thb=50");
		StringUtils.replace(htmlBody, "contentfeederURL", "/UserImageFeeder?username="+userName+"&#38;thb=50");
		//htmlBody.replaceAll("username", getServicesUser().getName(notif.userFrom));
		StringUtils.replace(htmlBody, "username", fullName);
		//htmlBody.replaceAll("fechahora", notif.formattedDate);
		StringUtils.replace(htmlBody, "fechahora", formattedDate);
		//htmlBody.replaceAll("mensaje", notif.text);
		StringUtils.replace(htmlBody, "mensaje", message);
		System.out.println("NotificationsBean.sendMail():: "+htmlBody);
		
		return sendMail(null, to, null, null, subject, htmlBody, "text/html");
		
	}

	
	public boolean sendMail(String from, String to, String cc,
			String bcc, String subject, String body, String contentType) {

		Session session = null;
		boolean retVal = false;
		//String username = "";
		//String password = "";			
		
		try {
			/*session = (Session) PortableRemoteObject.narrow(
					new InitialContext().lookup("java:Mail"), Session.class);*/
			
			session = getMailSession();
			//username = (String) session.getProperties().get("mail.smtps.user");
			//password = (String) session.getProperties().get("mail.smtps.password");			
			
			System.out.println("PartuzaMailer.sendMail(): "+session.getProperties());
			
		} catch (javax.naming.NamingException e) {
			e.printStackTrace();
		}

		try {
			MimeMessage message = new MimeMessage(session);
			if (from != null && !from.trim().equals("")) {
				message.setFrom(new InternetAddress(from));
			} else {
				message.setFrom(new InternetAddress(session.getProperties().getProperty("mail.from")));
				
			}
			if (to != null && !to.trim().equals("")) {
				message.setRecipients(Message.RecipientType.TO, InternetAddress
						.parse(to, false));
			}
			if (bcc != null && !bcc.trim().equals("")) {
				message.setRecipients(Message.RecipientType.BCC,
						InternetAddress.parse(bcc, false));
			}
			if (cc != null && !cc.trim().equals("")) {
				message.setRecipients(Message.RecipientType.CC, InternetAddress
						.parse(cc, false));
			}
			message.setSubject(subject);
			message.setContent(body, contentType);
			message.setSentDate(new Date());
			
			Transport.send(message);
			/*Transport transport = session.getTransport("smtps");
			try {
				transport.connect(username, password);
				transport.sendMessage(message, message.getAllRecipients());
				org.apache.log4j.Logger.getLogger(PartuzaMailer.class).warn("Message sent");
			}
			finally {
				transport.close();
			}*/			
			
			org.apache.log4j.Logger.getLogger(PartuzaMailer.class).warn("Message sent");
			retVal = true;
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
		return retVal;
	}

	private Session getMailSession() throws NamingException {
		if (this.session == null){
			Properties props = new Properties();
			InitialContext ictx = new InitialContext(props);
			//session = (Session) ictx.lookup("java:/Mail");
			this.session = (Session) ictx.lookup("java:/Mail");
		}
		return this.session;
	}
	
	
}
