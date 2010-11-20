package partuzabook.integracion.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import partuzabook.datos.persistencia.beans.Event;
import partuzabook.integracion.datatypes.DatatypeEvento;
import partuzabook.integracion.entityTranslators.ITranslatable;
import partuzabook.integracion.entityTranslators.TranslatorIntegracionEvent;

public class TranslatorCollection {
	public static List<DatatypeEvento> translateIntegracionEvent(List<Event> from) {
		return translateList(from, new TranslatorIntegracionEvent());
	}

	private static List translateList(List from,
			ITranslatable translator) {
		List to = new ArrayList();
		return (List)translateCollection(from, to, translator);
	}
	
	private static Set translateSet(Set from,
			ITranslatable translator) {
		Set to = new HashSet();
		return (Set)translateCollection(from, to, translator);
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
