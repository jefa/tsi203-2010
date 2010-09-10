import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;


public class Main {

	public static void main(String[] args) {
		try {
			Properties props = new Properties();
			props.setProperty( "java.naming.factory.initial",
				"org.jnp.interfaces.NamingContextFactory" );
			props.setProperty( "java.naming.provider.url", "127.0.0.1:1099" );

        	InitialContext ic = new InitialContext(props);
            ConnectionFactory cf = (ConnectionFactory)ic.lookup("ConnectionFactory");
            Connection connection = cf.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            TextMessage msg = session.createTextMessage("Hello!");

            String destinationName = "queue/MessageBeanTestQueue";
            Queue queue = (Queue)ic.lookup(destinationName);
            MessageProducer sender = session.createProducer(queue);
            sender.send(msg);

         	String destinationNameOut = "queue/MessageBeanTestQueueOut";
            Queue queueOut = (Queue)ic.lookup(destinationNameOut);
        	MessageConsumer consumer = session.createConsumer(queueOut);

        	TextMessage msgRcv = null;
        	while (msgRcv == null) {
        		msgRcv = (TextMessage)consumer.receive(2000);
        	}

        	System.out.println("Respuesta del servidor: " + msgRcv.getText());

        	consumer.close();
        	sender.close();
        	session.close();
        	connection.close();
        	
		} catch (Exception e) {
        	System.out.println(e);
        }
	}

}