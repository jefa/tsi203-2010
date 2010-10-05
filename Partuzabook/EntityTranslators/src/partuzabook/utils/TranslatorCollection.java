package partuzabook.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeNotification;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.datos.persistencia.beans.Content;
import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.NormalUser;
import partuzabook.datos.persistencia.beans.Notification;
import partuzabook.entityTranslators.ITranslatable;
import partuzabook.entityTranslators.TranslatorContent;
import partuzabook.entityTranslators.TranslatorEventSummary;
import partuzabook.entityTranslators.TranslatorNotification;
import partuzabook.entityTranslators.TranslatorUser;

public class TranslatorCollection {
	public static List<DatatypeEventSummary> translateEventSummary(List<Event> from) {
		return translateList(from, new TranslatorEventSummary());
	}

	public static List<DatatypeContent> translateContent(List<Content> from) {
		return translateList(from, new TranslatorContent());
	}

	public static List<DatatypeUser> translateNormalUser(List<NormalUser> from) {
		return translateList(from, new TranslatorUser());
	}
	
	public static List<DatatypeNotification> translateNotification(List<Notification> from) {
		return translateList(from, new TranslatorNotification());
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
		Iterator it = from.iterator();
		while (it.hasNext()) {
			to.add(translator.translate(it.next()));
		}
		return to;
	}
	
}
