package partuzabook.entityTranslators;

import partuzabook.datatypes.DatatypeUser;
import partuzabook.datos.persistencia.beans.Admin;
import partuzabook.datos.persistencia.beans.NormalUser;
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
		} else {
			dat.type  = DatatypeUser.NORMALUSER;
			NormalUser nu = (NormalUser) ent;
			if (nu.isFacebookUser() == null || nu.isFacebookUser() == false)
			{
				dat.usuarioFacebook = false;
				dat.facebookId = 0;
			}
			else
			{
				dat.usuarioFacebook = nu.isFacebookUser();
				dat.facebookId = nu.getFacebookId();
			}
		}
		dat.email = ent.getEmail();
		dat.imagePath = ent.getImgPath();
		return dat;
	}
}
