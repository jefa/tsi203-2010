package partuzabook.entityTranslators;

import com.sun.media.sound.DataPusher;

import partuzabook.datatypes.DatatypeMostTagged;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.datos.persistencia.beans.Admin;
import partuzabook.datos.persistencia.beans.NormalUser;

public class TranslatorMostTagged implements ITranslatable {
	
	public Object translate(Object iEnt) {
		NormalUser ent = (NormalUser)iEnt;
		DatatypeMostTagged dat = new DatatypeMostTagged();
		dat.user = (DatatypeUser)new TranslatorUser().translate(ent);
		dat.cantTags = ent.getMyTags().size();
		return dat;
	}
}
