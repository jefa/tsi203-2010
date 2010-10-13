package partuzabook.entityTranslators;

import partuzabook.datatypes.DatatypeUser;
import partuzabook.datos.persistencia.beans.Admin;
import partuzabook.datos.persistencia.beans.Client;
import partuzabook.datos.persistencia.beans.User;

public class TranslatorUser implements ITranslatable {
	
	public Object translate(Object iEnt) {
		User ent = (User)iEnt;
		DatatypeUser dat = new DatatypeUser();
		dat.username = ent.getUsername();
		dat.name = ent.getName();
		// Set type
		if (ent instanceof Admin){
			dat.type = DatatypeUser.ADMIN;
		} else if (ent instanceof Client) {
			dat.type = DatatypeUser.CLIENT;
		} else {
			dat.type  = DatatypeUser.NORMALUSER;
		}
		dat.email = ent.getEmail();
		dat.imagePath = ent.getImgPath();
		return dat;
	}
}
