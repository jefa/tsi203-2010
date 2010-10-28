package partuzabook.entityTranslators;

import java.util.List;

import partuzabook.datatypes.DatatypeAlbum;
import partuzabook.datatypes.DatatypeEvent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datos.persistencia.beans.Content;
import partuzabook.datos.persistencia.beans.Event;
import partuzabook.utils.TranslatorCollection;

public class TranslatorAlbum implements ITranslatable {
	
	public Object translate(Object iEnt) {
		List<Content> ent = (List<Content>)iEnt;
		DatatypeAlbum dat = new DatatypeAlbum();
		dat.setContents(TranslatorCollection.translateContent(ent));
		return dat;
	}
}
