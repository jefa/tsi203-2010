package partuzabook.serviciosNotificaciones.email;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

public class PartuzaMailer {
	
	public static boolean sendMail(String from, String to, String cc,
			String bcc, String subject, String body, String contentType) {

		Session session = null;
		boolean retVal = false;
		//String username = "";
		//String password = "";			
		
		try {
			/*session = (Session) PortableRemoteObject.narrow(
					new InitialContext().lookup("java:Mail"), Session.class);*/
			
			Properties props = new Properties();
			InitialContext ictx = new InitialContext(props);
			session = (Session) ictx.lookup("java:/Mail");
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
}
