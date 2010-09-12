

import java.util.Properties;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

import edu.tsi.Practico.Proxy.*;
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

	//FIXME En realidad deberíamos estar invocando al ws que se encarga de invocar a este a y SimpleWS2\
	/*
	@WebServiceRef(wsdlLocation = "http://127.0.0.1:8080/WebProject/SimpleWS1?wsdl")
    static SimpleWS1Service_Service serviceWS1;
	@WebServiceRef(wsdlLocation = "http://127.0.0.1:8080/WebProject/SimpleWS2?wsdl")
    static SimpleWS2Service_Service serviceWS2;
	*/
	
	//FIXME No esta bueno tener las ctes repetidas en el cliente y en el bean, si alguien ve una manera mas prolija, sirvace arreglarlo.
	//		Se podría hacer que el cliente mande directamente el wsdl que quiere utilizar para invocar un ws, con lo que no se precisan las ctes. 
	private static final int WS1 = 1;
	private static final int WS2 = 2;
	
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
            //String result = "Hello, " + text;
            //Ahora vamos a utilizar el result del ws1 o del ws2
            String result = "NO RESULT";
            // System.out.println("Reply: " + result);
            
            /*
            String endpoint ="http://ws.apache.org:5049/axis/services/echo";
            Service  service = new Service();
            Call     call    = (Call) service.createCall();
            call.setTargetEndpointAddress( new java.net.URL(endpoint) );
            call.setOperationName(new QName("http://soapinterop.org/", echoString));
            String ret = (String) call.invoke( new Object[] { "Hello!" } );
            System.out.println("Sent 'Hello!', got '" + ret + "'");
              */    
            int index = text.indexOf('_');
            
            if(index == -1) System.err.println("NO SE MANDO WS EN EL MENSAJE");
            
            int WS = Integer.parseInt(text.substring(0, index));
            String userName = text.substring(index + 1);
            
            
            
            //TODO: ME QUEDA LA DUDA DE SI DE ESTA MANERA ESTAMOS INVOCANDO AL WS REMOTAMENTE
            // DE TODAS MANERAS, EN REALIDAD TENDRIA QUE PASARLE ESTOS PARÁMETROS A OTRA CLASE QUE ADMINITRE LOS WS EN REALIDAD...
            try { // Call Web Service Operation
                System.out.println(
                        "Retrieving the port from the following service: "
                        + WS);
                
                switch(WS) {
                
                case WS1: 
                	SimpleWS1Service_PortType simpleWS1 = new SimpleWS1Service_ServiceLocator().getSimpleWS1ServicePort();
                	result = simpleWS1.invoke(userName);
                	break;
                case WS2:
                	SimpleWS2Service_PortType simpleWS2 = new SimpleWS2Service_ServiceLocator().getSimpleWS2ServicePort();
                	result = simpleWS2.invoke(userName);
                	break;
                default:
                	
                }
                
                System.out.println(result);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            
            TextMessage reply = session.createTextMessage(result);
            
            //TODO: VERIFICAR QUE CON ESTO ESTOY MANDANDOLE EL PAQUETE AL CLIENTE QUE ME INTERESA
            reply.setJMSDestination(message.getJMSReplyTo());
            
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
