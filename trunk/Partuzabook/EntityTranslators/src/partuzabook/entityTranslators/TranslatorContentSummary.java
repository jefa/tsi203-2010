package partuzabook.entityTranslators;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datos.persistencia.beans.Content;
import partuzabook.datos.persistencia.beans.Photo;
import partuzabook.datos.persistencia.beans.Video;

public class TranslatorContentSummary implements ITranslatable {
	
	public Object translate(Object iEnt) {
		Content ent = (Content)iEnt;
		DatatypeContent dat = new DatatypeContent();
		dat.contId = ent.getCntIdAuto();
		dat.eventId = ent.getEvent().getEvtIdAuto();
		// Set type
		if (ent instanceof Photo){
			dat.type  = DatatypeContent.PHOTO;
		}
		else if (ent instanceof Video) {
			dat.type = DatatypeContent.VIDEO;
		}
		else {
			dat.type = DatatypeContent.EXTERNAL;
		}
		return dat;
	}
}
