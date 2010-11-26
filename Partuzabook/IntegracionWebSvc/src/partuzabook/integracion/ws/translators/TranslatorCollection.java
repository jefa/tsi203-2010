package partuzabook.integracion.ws.translators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.integracion.ws.busqueda.Evento;
import partuzabook.integracion.ws.contenido.Contenido;
import partuzabook.integracion.ws.contenido.Contenido_Type;
import partuzabook.integracion.ws.productora_web.DataContent;
import partuzabook.integracion.ws.productora_web.DataEvent;

public class TranslatorCollection {
	static public List<DatatypeEventSummary> translateFromEvento(List<Evento> from) {
		return (List<DatatypeEventSummary>)translateList(from, new TranslatorEventoDatatypeEventSummary());
	}
	
	static public List<DatatypeEventSummary> translateFromDataEvent(List<DataEvent> from) {
		return (List<DatatypeEventSummary>)translateList(from, new TranslatorDataEventDatatypeEventSummary());
	}
	
	public static List<DatatypeContent> translateFromContenido_Type(List<Contenido_Type> from) {
		return (List<DatatypeContent>)translateList(from, new TranslatorContenido_TypeDatatypeContent());
	}
	
	public static List<DatatypeContent> translateFromDataContent(List<DataContent> from) {
		return (List<DatatypeContent>)translateList(from, new TranslatorDataContentDatatypeContent());
	}
	
	private static List translateList(List from,
			ITranslatable translator) {
		List to = new ArrayList();
		return (List)translateCollection(from, to, translator);
	}
	
	private static Collection translateCollection(Collection from, Collection to,
			ITranslatable translator) {
		if (from != null) {
			Iterator it = from.iterator();
			while (it.hasNext()) {
				to.add(translator.translate(it.next()));
			}
		}
		return to;
	}

}
