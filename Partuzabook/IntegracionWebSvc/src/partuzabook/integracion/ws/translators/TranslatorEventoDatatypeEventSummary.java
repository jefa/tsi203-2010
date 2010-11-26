package partuzabook.integracion.ws.translators;

import java.util.Date;
import javax.xml.datatype.XMLGregorianCalendar;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.integracion.ws.busqueda.Evento;

public class TranslatorEventoDatatypeEventSummary implements ITranslatable {
	
	public Object translate(Object iEnt) {
		Evento evento = (Evento)iEnt;
		DatatypeEventSummary dat = new DatatypeEventSummary();
		dat.setEvtName(evento.getNombre());
		dat.setEvtId(evento.getIdEvento());
		dat.setDate(new Date(evento.getFecha().getMillisecond()));
		dat.setDescription(evento.getDescripcion());
		dat.setAddress(evento.getDireccion());
		if (evento.getUrlCover() == null || evento.getUrlCover() == "") {
			dat.setCoverUrl(null);
		}
		else {
			dat.setCoverUrl(evento.getUrlCover());
		}
		return dat;
	}
}
