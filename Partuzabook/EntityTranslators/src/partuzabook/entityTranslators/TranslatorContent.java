package partuzabook.entityTranslators;

import java.util.ArrayList;
import java.util.Iterator;

import partuzabook.datatypes.DatatypeComment;
import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeRating;
import partuzabook.datatypes.DatatypeTag;
import partuzabook.datos.persistencia.beans.Content;
import partuzabook.datos.persistencia.beans.Comment;
import partuzabook.datos.persistencia.beans.Photo;
import partuzabook.datos.persistencia.beans.Rating;
import partuzabook.datos.persistencia.beans.Tag;
import partuzabook.datos.persistencia.beans.Video;

public class TranslatorContent implements ITranslatable {
	
	public Object translate(Object iEnt) {
		if(!(iEnt instanceof Content)){
			//TODO: throw exception			
		}			
		Content ent = (Content)iEnt;
		DatatypeContent dat = new DatatypeContent();
		dat.contId = ent.getCntIdAuto();
		// Translate list of Comments
		dat.comments = new ArrayList<DatatypeComment>();
		Iterator<Comment> itCom = ent.getComments().iterator();
		TranslatorComment transCom = new TranslatorComment();
		while (itCom.hasNext()) {
			dat.comments.add((DatatypeComment) transCom.translate(itCom.next()));		
		}
		// Translate list of Ratings
		dat.ratings = new ArrayList<DatatypeRating>();
		Iterator<Rating> itRat = ent.getRatings().iterator();
		TranslatorRating transRat = new TranslatorRating();
		while (itRat.hasNext()) {
			dat.ratings.add((DatatypeRating) transRat.translate(itRat.next()));		
		}
		// Translate list of Tags
		dat.tags=  new ArrayList<DatatypeTag>();
		Iterator<Tag> itTag = ent.getTags().iterator();
		TranslatorTag transTag = new TranslatorTag();
		while (itTag.hasNext()) {
			dat.tags.add((DatatypeTag) transTag.translate(itTag.next()));		
		}
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