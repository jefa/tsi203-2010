package partuzabook.entityTranslators;

import partuzabook.datatypes.DatatypeRating;
import partuzabook.datos.persistencia.beans.Rating;

public class TranslatorRating implements ITranslatable {
	
	public Object translate(Object iEnt) {
		Rating rat = (Rating)iEnt;
		DatatypeRating dat = new DatatypeRating();
		dat.contentId = rat.getContent().getCntIdAuto();
		dat.score = rat.getScore();
		dat.userName = rat.getUser().getName();
		return dat;
	}
}
