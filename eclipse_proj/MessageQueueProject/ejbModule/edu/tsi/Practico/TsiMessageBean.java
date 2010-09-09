package edu.tsi.Practico;

import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue"
		),
		@ActivationConfigProperty(
				propertyName = "destination", propertyValue = "queue/TsiQueue"
		)}, 
		mappedName = "queue/TsiQueue")
		
public class TsiMessageBean implements MessageListener {
    static final Logger logger = Logger.getLogger("TsiMessageBean");
    @Resource
    private MessageDrivenContext mdc;

    public TsiMessageBean() {
    }
	
	public void onMessage(Message message) {
		TextMessage msg = null;
		try {
			if (message instanceof TextMessage) {
				msg = (TextMessage) message;
				logger.info("MESSAGE BEAN: Message received: " + msg.getText());
			} else {
				logger.warning("Message of wrong type: "
						+ message.getClass().getName());
			}
		} catch (JMSException e) {
			e.printStackTrace();
			mdc.setRollbackOnly();
		} catch (Throwable te) {
			te.printStackTrace();
		}
	}
}
