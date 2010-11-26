package partuzabook.entityTranslators;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datos.persistencia.beans.Content;
import partuzabook.datos.persistencia.beans.ExternalPhoto;
import partuzabook.datos.persistencia.beans.ExternalVideo;
import partuzabook.datos.persistencia.beans.Photo;
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
		}
		else if (ent instanceof Video) {
			dat.setType(DatatypeContent.VIDEO);
		}
		else if (ent instanceof ExternalPhoto){
			dat.setType(DatatypeContent.EXTERNAL_PHOTO);
		}
		else if (ent instanceof ExternalVideo){
			dat.setType(DatatypeContent.EXTERNAL_VIDEO);
		}
		dat.setDescription(ent.getDescription());
		if (ent instanceof Photo || ent instanceof Video) {
			dat.setUrl(ent.getCntIdAuto().toString());
		}
		else {
			dat.setUrl(ent.getUrl());
		}
		dat.setUploadedBy(ent.getUser().getName());
		return dat;
	}
}
