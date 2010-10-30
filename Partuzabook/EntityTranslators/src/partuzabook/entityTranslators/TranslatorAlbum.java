package partuzabook.entityTranslators;

import java.util.List;

import partuzabook.datatypes.DatatypeAlbum;
import partuzabook.datatypes.DatatypeEvent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datos.persistencia.beans.Album;
import partuzabook.datos.persistencia.beans.Content;
import partuzabook.datos.persistencia.beans.Event;
import partuzabook.utils.TranslatorCollection;

public class TranslatorAlbum implements ITranslatable {
	
	public Object translate(Object iEnt) {
		Album album = (Album)iEnt;
		TranslatorEvent transEv = new TranslatorEvent();
		DatatypeEvent dataEvent = (DatatypeEvent) transEv.translate(album.getEvent());
		
		TranslatorEventSummary transEvSum = new TranslatorEventSummary();
		DatatypeEventSummary dataEventSum = (DatatypeEventSummary) transEvSum.translate(album.getEvent());

		DatatypeAlbum dat = new DatatypeAlbum();
		dat.setEvent(dataEvent);
		dat.setEventSummary(dataEventSum);

		return dat;
	}
}
