package partuzabook.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.entityTranslators.ITranslatable;
import partuzabook.entityTranslators.TranslatorContent;
import partuzabook.entityTranslators.TranslatorEventSummary;
import partuzabook.entityTranslators.TranslatorUser;

public class TranslatorCollection {
	public static List<DatatypeEventSummary> translateEventSummary(List from) {
		return translateList(from, new TranslatorEventSummary());
	}

	public static List<DatatypeContent> translateContent(List from) {
		return translateList(from, new TranslatorContent());
	}

	public static List<DatatypeUser> translateUser(List from) {
		return translateList(from, new TranslatorUser());
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
