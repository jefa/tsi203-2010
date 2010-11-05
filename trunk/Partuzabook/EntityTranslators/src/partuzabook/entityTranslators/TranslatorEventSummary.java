package partuzabook.entityTranslators;

import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datos.persistencia.beans.Event;
import partuzabook.utils.TranslatorCollection;

public class TranslatorEventSummary implements ITranslatable {
	
	public Object translate(Object iEnt) {
		Event ent = (Event)iEnt;
		DatatypeEventSummary dat = new DatatypeEventSummary();
		dat.setEvtName(ent.getEvtName());
		dat.setEvtId(ent.getEvtIdAuto());
		dat.setDate(ent.getDate());
		dat.setDescription(ent.getDescription());
		dat.setAddress(ent.getAddress());
		dat.setLatitude(ent.getLatitude());
		dat.setLongitude(ent.getLongitude());
		dat.setDuration(ent.getDuration());
		dat.setHasAlbum(ent.getAlbum() != null);
		if (ent.getCover() != null) {
			dat.setCoverId(ent.getCover().getCntIdAuto());
		}
		else {
			dat.setCoverId(-1);
		}
		if(ent.getEvtCategory() != null) {
			dat.setEventCategory(ent.getEvtCategory().getCategory());
		}
		if(ent.getMyMods() == null)
			dat.setModsUsernames(null);
		else
			dat.setModsUsernames(TranslatorCollection.translateModUser(ent.getMyMods()));
		
		if(ent.getMyParticipants() == null)
			dat.setParticipantsUsernames(null);
		else
			dat.setParticipantsUsernames(TranslatorCollection.translateParticipantUser(ent.getMyParticipants()));
		
		
		//dat.setHashtag("#kirchner");
		if(ent.getHashtag() == null)
			dat.setHashtag("");
		else
			dat.setHashtag(ent.getHashtag());
		
		return dat;
	}
}
