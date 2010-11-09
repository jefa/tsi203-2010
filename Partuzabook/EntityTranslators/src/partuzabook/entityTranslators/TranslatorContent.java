package partuzabook.entityTranslators;

import java.util.Iterator;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datos.persistencia.beans.Content;
import partuzabook.datos.persistencia.beans.Rating;
import partuzabook.utils.TranslatorCollection;

public class TranslatorContent extends TranslatorContentSummary implements ITranslatable {
	
	public Object translate(Object iEnt) {
		if(!(iEnt instanceof Content)){
			//TODO: throw exception			
		}			
		Content ent = (Content)iEnt;
		DatatypeContent dat = (DatatypeContent)super.translate(ent);
		dat.setEventName(ent.getEvent().getEvtName());
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
		
		dat.setPosGallery(ent.getPosGallery());
		dat.setPosAlbum(ent.getPosAlbum());
		return dat;
	}
}
