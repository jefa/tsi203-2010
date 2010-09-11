package edu.tsi.Practico.MDB;

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
	private static final int INICIAR_SESION = 1;
	private static final int FINALIZAR_SESION = 2;
	private static final int WS1 = 3;
	private static final int WS2 = 4;
	private static final int WS3 = 5;
	
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
            
            //ServicioCompuestoService scsl = null;
            //ServicioCompuesto servicioCompuesto = null;
            try {
            	/*
            	try {
        			scsl = new ServicioCompuestoServiceLocator();
            	
        			servicioCompuesto = scsl.getServicioCompuestoPort();
        		} catch (ServiceException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        		*/
            	//INVOCANDO AL WS1
            	//SimpleWS1Service_PortType hello = new SimpleWS1Service_ServiceLocator().getSimpleWS1ServicePort();
    		    //result = hello.invoke(userName);
            	
            	//FIXME
            	edu.tsi.Practico.SC.ServicioCompuesto servicioCompuesto = new edu.tsi.Practico.SC.ServicioCompuesto();
    		    
            	//edu.tsi.Practico.ServicioCompuestoService = new edu.tsi.Practico.ServicioCompuestoService();
            	//edu.tsi.Practico.ServicioCompuestoProxy a = new edu.tsi.Practico.ServicioCompuestoProxy();
            	//ServicioCompuesto servicioCompuesto = a.getServicioCompuesto();
                //ServicioCompuestoServiceLocator scsl = new ServicioCompuestoServiceLocator();
            	//ServicioCompuesto servicioCompuesto = scsl.getServicioCompuestoPort();
            	
                switch(WS) {
                
                case INICIAR_SESION:
                	
                	result = servicioCompuesto.inciarSesion();
                    
                	break;
                case FINALIZAR_SESION:
                	
                	result = servicioCompuesto.finalizarSesion(userName);
                	
                	break;
                case WS1:
                	
                	result = servicioCompuesto.invocarCombinacionWS1(userName);
                	
                	break;
                case WS2:
                	
                	result = servicioCompuesto.invocarCombinacionWS2(userName);
                	
                	break;
				case WS3:
					
					result = servicioCompuesto.invocarCombinacionWS3(userName);
					
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
