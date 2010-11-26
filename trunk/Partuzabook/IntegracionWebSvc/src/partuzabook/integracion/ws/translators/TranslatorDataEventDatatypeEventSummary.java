package partuzabook.integracion.ws.translators;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.XMLGregorianCalendar;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.integracion.ws.busqueda.Evento;
import partuzabook.integracion.ws.productora_web.DataEvent;

public class TranslatorDataEventDatatypeEventSummary implements ITranslatable {
	
	public Object translate(Object iEnt) {
		DataEvent dataEvent = (DataEvent)iEnt;
		DatatypeEventSummary dat = new DatatypeEventSummary();
		dat.setEvtName(dataEvent.getNombre().getValue());
		dat.setEvtId(dataEvent.getIdEvento().getValue());
		dat.setDate(dataEvent.getFecha().getValue().toGregorianCalendar().getTime());
		dat.setDescription(dataEvent.getDescripcion().getValue());
		dat.setAddress(dataEvent.getDireccion().getValue());
		if (dataEvent.getUrlPortada().getValue() == null || dataEvent.getUrlPortada().getValue() == "") {
			dat.setCoverUrl(null);
		}
		else {
			dat.setCoverUrl(dataEvent.getUrlPortada().getValue());
		}
		return dat;
	}
	
	public Object translateFrom(Object iEnt) {
		DatatypeEventSummary dat = (DatatypeEventSummary)iEnt;
		Evento evento = new Evento();
		evento.setNombre(dat.getEvtName());
		evento.setIdEvento(dat.getEvtId());
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(dat.getDate().getTime());
		XMLGregorianCalendar g = new XMLGregorianCalendarImpl(gc);
		evento.setFecha(g);
		evento.setDescripcion(dat.getDescription());
		evento.setDireccion(dat.getAddress());
		if (dat.getCoverUrl() == null || dat.getCoverUrl() == "") {
			evento.setUrlCover(null);
		}
		else {
			evento.setUrlCover(dat.getCoverUrl());
		}
		return evento;
	}
}
