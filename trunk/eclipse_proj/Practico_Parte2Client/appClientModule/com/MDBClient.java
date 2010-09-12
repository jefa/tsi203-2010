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
import javax.naming.NamingException;

import edu.tsi.Practico.MDB.MessagesMDBean;

public class MDBClient {
	
	private Properties props;
	private InitialContext ic;
	private static final String destinationName = "queue/MessageBeanTestQueue";
 	private static final String destinationNameOut = "queue/MessageBeanTestQueueOut";
	
	public MDBClient() {
		props = new Properties();
		props.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
		props.setProperty( "java.naming.provider.url", "127.0.0.1:1099" );
	
		try {
			ic = new InitialContext(props);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			System.out.println("[MDBClient]Excepcion en la creacion del MDBClient, no esta pudiendo obtener el InitialContext");
			e.printStackTrace();
		}
	}
	
	private String sendAndReceiveMessage(String textToSend) {
		try {
			
	        ConnectionFactory cf = (ConnectionFactory)ic.lookup("ConnectionFactory");
	        Connection connection = cf.createConnection();
	        connection.start();
	        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	
	        TextMessage msg = session.createTextMessage(textToSend);
	
	        //Envio el mensaje
	        Queue queue = (Queue)ic.lookup(destinationName);
	        MessageProducer sender = session.createProducer(queue);
	        sender.send(msg);
	
	        //Polling consumer esperando la respuesta
	        Queue queueOut = (Queue)ic.lookup(destinationNameOut);
	    	MessageConsumer consumer = session.createConsumer(queueOut);
	
	    	TextMessage msgRcv = null;
	    	while (msgRcv == null) {
	    		msgRcv = (TextMessage)consumer.receive(2000);
	    	}
	
	    	String response = msgRcv.getText();
	    	System.out.println("[MDBClient]Respuesta recibida por el servidor: " + response);
	
	    	sender.close();
	    	consumer.close();
	    	session.close();
	    	connection.close();
	    	
	    	return response;
		} catch (Exception e) {
	    	e.printStackTrace();
	    	return "[MDBClient]ERROR in comunication";
	    }
	}
	
	/**
	 * Inicia la sesion con el servidor y retorna en id creado, el cual debe ser enviado en las demas invocaciones
	 * @return ID de la sesion del usuario
	 * */
	public String iniciarSesion() {
		return this.sendAndReceiveMessage(MessagesMDBean.INICIAR_SESION + "_");
	}	
	
	/**
	 * Finaliza la sesion con el servidor, retornando los logs de actividad del usuario
	 * @param sessionID - El String devuleto al invocar inciarSesion()
	 * @return Logs de actividad del ID pasado como parametro
	 * */
	public String finalizarSesion(String sessionID) {
		return this.sendAndReceiveMessage(MessagesMDBean.FINALIZAR_SESION + "_" + sessionID);
	}
	
	/**
	 * Invoca al WS1, con el parametro param usando como ID sessionID. Retorna el resultado de ejecutar WS1.
	 * @param sessionID - El String devuleto al invocar inciarSesion()
	 * @param param - Parametro para WS1
	 * @return Resultado de ejecutar WS1
	 * */
	public String invocarWS1(String sessionID, String param) {
		return this.sendAndReceiveMessage(MessagesMDBean.WS1 + "_" + sessionID + "_" + param);
	}	
	
	/**
	 * Invoca al WS2, con el parametro param usando como ID sessionID. Retorna el resultado de ejecutar WS2.
	 * @param sessionID - El String devuleto al invocar inciarSesion()
	 * @param param - Parametro para WS2
	 * @return Resultado de ejecutar WS2
	 * */
	public String invocarWS2(String sessionID, String param) {
		return this.sendAndReceiveMessage(MessagesMDBean.WS2 + "_" + sessionID + "_" + param);
	}
	
}
