package partuzabook.entityTranslators;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datos.persistencia.beans.Content;

public class TranslatorContent implements ITranslatable {
	
	public Object translate(Object iEnt) {
		Content ent = (Content)iEnt;
		DatatypeContent dat = new DatatypeContent();
		//TODO: dat.url = ent.getUrl();
		return dat;
	}
}
