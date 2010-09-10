

import java.util.Properties;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.*;
import javax.naming.*;

/**
 * Message-Driven Bean implementation class for: MessagesMDBean
 *
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
							@ActivationConfigProperty(
				propertyName = "destination", propertyValue = "queue/MessageBeanTestQueue")
							},
		mappedName = "queue/MessageBeanTestQueue" )
public class MessagesMDBean implements MessageListener {

    public MessagesMDBean() {
        // TODO Auto-generated constructor stub
    }
	
    public void onMessage(Message message) {
    	Properties props = new Properties();
    	props.setProperty( "java.naming.factory.initial",
	    	"org.jnp.interfaces.NamingContextFactory" );
	    props.setProperty( "java.naming.provider.url", "127.0.0.1:1099" );
        	
        try {
			InitialContext ic = new InitialContext(props);
			ConnectionFactory cf = (ConnectionFactory)ic.lookup("ConnectionFactory");
			Connection conn = cf.createConnection();
            conn.start();
            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            
            TextMessage tm = (TextMessage) message;
            String text = tm.getText();
            System.out.println("Message Received: " + text );
            String result = "Hello, " + text;
            System.out.println("Reply: " + result);
            TextMessage reply = session.createTextMessage(result);

            String destinationName = "queue/MessageBeanTestQueueOut";
            Queue queue = (Queue)ic.lookup(destinationName);
            MessageProducer producer = session.createProducer(queue);
            producer.send(reply);

            producer.close();
            conn.close();
            
        } catch (Exception e){
        	e.printStackTrace();
        }
    	
    }

    
}
