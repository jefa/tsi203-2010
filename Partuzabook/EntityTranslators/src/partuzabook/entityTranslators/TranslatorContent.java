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
		dat.contId = ent.getCntIdAuto();
		dat.eventName = ent.getEvent().getEvtName();
		dat.eventId = ent.getEvent().getEvtIdAuto();
		// Translate list of Comments
		dat.comments = TranslatorCollection.translateComments(ent.getComments());
		
		// Translate list of Ratings
		dat.ratings = new ArrayList<DatatypeRating>();
		Iterator<Rating> itRat = ent.getRatings().iterator();
		TranslatorRating transRat = new TranslatorRating();
		int avg_score = 0;
		while (itRat.hasNext()) {
			Rating r = itRat.next();
			dat.ratings.add((DatatypeRating) transRat.translate(r));
			avg_score += r.getScore();
			
		}
		//Add average score of the content
		if(ent.getRatings()!= null && ent.getRatings().size() > 0) {
			dat.avgScore = (double) (avg_score / ent.getRatings().size());
		}
		else {
			dat.avgScore = 0.0;
		}
		
		dat.tags = TranslatorCollection.translateTag(ent.getTags());
		
		// Set type
		if (ent instanceof Photo){
			dat.type  = DatatypeContent.PHOTO;
		} else if (ent instanceof Video) {
			dat.type = DatatypeContent.VIDEO;
		} else {
			dat.type = DatatypeContent.EXTERNAL;
		}
		dat.pos = ent.getPos();
		
		return dat;
	}
}
