package partuzabook.entityTranslators;

import partuzabook.datatypes.DatatypeNotification;
import partuzabook.datos.persistencia.beans.Notification;

public class TranslatorNotification implements ITranslatable {
	
	public Object translate(Object iEnt) {
		Notification ent = (Notification)iEnt;
		DatatypeNotification dat = new DatatypeNotification();
    	dat.notDate = ent.getNotDate();
    	dat.read = ent.getRead();
    	dat.reference = ent.getReference();
    	dat.text = ent.getText();
    	dat.type = ent.getType();
    	dat.userFrom = ent.getUserFrom().getUsername();
    	dat.userTo = ent.getUserTo().getUsername();
		return dat;
	}
}
