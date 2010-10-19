package partuzabook.entityTranslators;

import partuzabook.datos.persistencia.beans.User;

public class TranslatorModUser implements ITranslatable {
	
	public Object translate(Object iEnt) {
		User ent = (User)iEnt;
		return ent.getUsername();
	}
}
