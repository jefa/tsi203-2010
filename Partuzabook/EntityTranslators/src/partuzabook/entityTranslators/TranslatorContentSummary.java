package partuzabook.entityTranslators;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datos.persistencia.beans.Content;
import partuzabook.datos.persistencia.beans.Photo;
import partuzabook.datos.persistencia.beans.SelfContent;
import partuzabook.datos.persistencia.beans.Video;

public class TranslatorContentSummary implements ITranslatable {
	
	public Object translate(Object iEnt) {
		Content ent = (Content)iEnt;
		DatatypeContent dat = new DatatypeContent();
		dat.setContId(ent.getCntIdAuto());
		dat.setEventId(ent.getEvent().getEvtIdAuto());
		// Set type
		if (ent instanceof Photo){
			dat.setType(DatatypeContent.PHOTO);
			dat.setDescription(((SelfContent)(ent)).getDescription());
		}
		else if (ent instanceof Video) {
			dat.setType(DatatypeContent.VIDEO);
			dat.setDescription(((SelfContent)(ent)).getDescription());
		}
		else {
			dat.setType(DatatypeContent.EXTERNAL);
		}
		dat.setUrl(ent.getUrl());
		return dat;
	}
}
