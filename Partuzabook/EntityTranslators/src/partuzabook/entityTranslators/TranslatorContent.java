package partuzabook.entityTranslators;

import java.util.ArrayList;
import java.util.Iterator;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeRating;
import partuzabook.datos.persistencia.beans.Content;
import partuzabook.datos.persistencia.beans.Photo;
import partuzabook.datos.persistencia.beans.Rating;
import partuzabook.datos.persistencia.beans.Video;
import partuzabook.utils.TranslatorCollection;

public class TranslatorContent implements ITranslatable {
	
	public Object translate(Object iEnt) {
		if(!(iEnt instanceof Content)){
			//TODO: throw exception			
		}			
		Content ent = (Content)iEnt;
		DatatypeContent dat = new DatatypeContent();
		dat.setContId(ent.getCntIdAuto());
		dat.setEventName(ent.getEvent().getEvtName());
		dat.setEventId(ent.getEvent().getEvtIdAuto());
		dat.setCategories(TranslatorCollection.translateContentCategoriesSummary(ent.getCntCategories()));
		// Translate list of Comments
		dat.setComments(TranslatorCollection.translateComments(ent.getComments()));
		
		// Translate list of Ratings
		Iterator<Rating> itRat = ent.getRatings().iterator();
		double avg_score = 0;
		while (itRat.hasNext()) {
			Rating r = itRat.next();
			avg_score += r.getScore();
		}
		//Add average score of the content
		if(ent.getRatings()!= null && ent.getRatings().size() > 0) {
			dat.setAvgScore((double) (avg_score / ent.getRatings().size()));
		}
		else {
			dat.setAvgScore(0.0);
		}
		
		dat.setTags(TranslatorCollection.translateTag(ent.getTags()));
		
		// Set type
		if (ent instanceof Photo){
			dat.setType(DatatypeContent.PHOTO);
		} else if (ent instanceof Video) {
			dat.setType(DatatypeContent.VIDEO);
		} else {
			dat.setType(DatatypeContent.EXTERNAL);
		}
		dat.setPos(ent.getPosGallery());
		
		return dat;
	}
}
