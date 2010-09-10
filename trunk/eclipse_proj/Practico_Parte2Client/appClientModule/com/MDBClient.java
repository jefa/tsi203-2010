package com;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

public class MDBClient {
	
	//De alguna manera debo de poder diferenciar que ws quiero invocar, por lo que los mensajes enviados son username y ws
	public static final int WS1 = 1;
	public static final int WS2 = 2;

	public String invoke(String userName, int WS) {
		try {
			
			if(WS != WS1 && WS != WS2) return "ERROR: Debe ingresar un WS válido.";
			
			Properties props = new Properties();
			props.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
			props.setProperty( "java.naming.provider.url", "127.0.0.1:1099" );
	
	    	InitialContext ic = new InitialContext(props);
	        ConnectionFactory cf = (ConnectionFactory)ic.lookup("ConnectionFactory");
	        Connection connection = cf.createConnection();
	        connection.start();
	        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	
	        //Insertamos el WS a invocar y el nombre del usuario en el mensaje. El servidor debe parsearlo para saber a que WS invocar
	        TextMessage msg = session.createTextMessage(WS + "_" + userName);
	
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
	
	    	String response = msgRcv.getText();
	    	System.out.println("Respuesta del servidor: " + response);
	
	    	consumer.close();
	    	sender.close();
	    	session.close();
	    	connection.close();
	    	
	    	return response;
		} catch (Exception e) {
	    	e.printStackTrace();
	    	return "ERROR in comunication";
	    }
	}
}
