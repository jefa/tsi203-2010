package partuzabook.serviciosNotificaciones.queue;

import java.io.BufferedReader;
import java.io.StringReader;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import partuzabook.serviciosNotificaciones.email.PartuzaMailer;

@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
							@ActivationConfigProperty(
				propertyName = "destination", propertyValue = "queue/NotificacionesQueue")
							},
		mappedName = "queue/NotificacionesQueue" )
public class NotificacionesQueue implements MessageListener {
	
	private static String EMAIL_MSG_TYPE = "MailMessage";
	private static String NOTIF_MSG_TYPE = "NotifMessage";
	
	private PartuzaMailer mailer = new PartuzaMailer();
	
    public NotificacionesQueue() {
    }
	
    public void onMessage(Message message) {
        try {            
            TextMessage tm = (TextMessage) message;
			getLogger().log(Priority.DEBUG, "Message Received: " + tm.getText());            
            BufferedReader r = new BufferedReader(new StringReader(tm.getText()));
            String msgType = r.readLine();
            if (EMAIL_MSG_TYPE.equals(msgType)){
            	String from = r.readLine();
            	String to = r.readLine();
            	String subject = r.readLine();
            	String body = r.readLine();
            	String bodyTmp = "";
            	while (bodyTmp != null){
            		bodyTmp = r.readLine();
            		if (bodyTmp != null)
            			body = body + bodyTmp;
            	}
            	
    			mailer.sendMail(from, to, null, null, subject, body, "text/plain");
    			getLogger().log(Priority.DEBUG, "Message sent");
            	
            } else if (NOTIF_MSG_TYPE.equals(msgType)){
            	
            } else {
            	getLogger().error("Tipo de mensaje invalido: "+msgType);
            }
                        
        } catch (Exception e){
			getLogger().error("Error procesando notificacion: msg="+message, e);
        }
    }

	private Logger getLogger() {
		return Logger.getLogger(this.getClass());
	}
    
}
