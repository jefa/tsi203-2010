package edu.tsi.Practico.client;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;
import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.InitialContext;
//import javax.annotation.Resource;


public class MessageClient {
    //@Resource(mappedName = "ConnectionFactory")
    private static ConnectionFactory connectionFactory;
    //@Resource(mappedName = "queue/TsiQueue")
    private static Queue queue;

    public static void main(String[] args) {
        Connection connection = null;
        Session session = null;
        MessageProducer messageProducer = null;
        TextMessage message = null;
        final int NUM_MSGS = 3;

        try {
        	
        	String queueName = "queue/TsiQueue";
        	Properties props = new Properties();
        	props.setProperty( "java.naming.factory.initial",
        		"org.jnp.interfaces.NamingContextFactory" );
        	props.setProperty( "java.naming.provider.url", "127.0.0.1:1099" );
        	Context initialContext = new InitialContext(props);        	
        	
        	connectionFactory  = (ConnectionFactory) initialContext.lookup("ConnectionFactory");
            connection = connectionFactory.createConnection();
        	queue  = (Queue) initialContext.lookup(queueName);
            
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(queue);
            message = session.createTextMessage();

            for (int i = 0; i < NUM_MSGS; i++) {
                message.setText("This is message " + (i + 1));
                System.out.println("Sending message: " + message.getText());
                messageProducer.send(message);
            }

            System.out.println("To see if the bean received the messages,");
            System.out.println(
                    " check <install_dir>/domains/domain1/logs/server.log.");
        } catch (/*JMS*/Exception e) {
        	e.printStackTrace();
            System.out.println("Exception occurred: " + e.toString());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                }
            } // if

            System.exit(0);
        } // finally
    } // main
} // class
