package partuzabook.entityTranslators;

import partuzabook.datatypes.DatatypeUser;
import partuzabook.datos.persistencia.beans.User;

public class TranslatorUser implements ITranslatable {
	
	public Object translate(Object iEnt) {
		User ent = (User)iEnt;
		DatatypeUser dat = new DatatypeUser();
		dat.username = ent.getUsername();
		//TODO dat.name = ent.getName();
		return dat;
	}
}
