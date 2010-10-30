package partuzabook.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import partuzabook.datatypes.DatatypeAlbum;
import partuzabook.datatypes.DatatypeCategorySummary;
import partuzabook.datatypes.DatatypeComment;
import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeMostTagged;
import partuzabook.datatypes.DatatypeNotification;
import partuzabook.datatypes.DatatypeTag;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.datos.persistencia.beans.Album;
import partuzabook.datos.persistencia.beans.CntCategory;
import partuzabook.datos.persistencia.beans.Comment;
import partuzabook.datos.persistencia.beans.Content;
import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.NormalUser;
import partuzabook.datos.persistencia.beans.Notification;
import partuzabook.datos.persistencia.beans.Tag;
import partuzabook.entityTranslators.ITranslatable;
import partuzabook.entityTranslators.TranslatorAlbum;
import partuzabook.entityTranslators.TranslatorCategorySummary;
import partuzabook.entityTranslators.TranslatorComment;
import partuzabook.entityTranslators.TranslatorContent;
import partuzabook.entityTranslators.TranslatorContentSummary;
import partuzabook.entityTranslators.TranslatorEventSummary;
import partuzabook.entityTranslators.TranslatorModUser;
import partuzabook.entityTranslators.TranslatorMostTagged;
import partuzabook.entityTranslators.TranslatorNotification;
import partuzabook.entityTranslators.TranslatorTag;
import partuzabook.entityTranslators.TranslatorUser;

public class TranslatorCollection {
	public static List<DatatypeEventSummary> translateEventSummary(List<Event> from) {
		return translateList(from, new TranslatorEventSummary());
	}

	public static List<DatatypeAlbum> translateAlbums(List<Album> from) {
		return translateList(from, new TranslatorAlbum());
	}

	public static List<DatatypeCategorySummary> translateContentCategoriesSummary(List<CntCategory> from) {
		return translateList(from, new TranslatorCategorySummary());
	}
	
	public static List<DatatypeContent> translateContent(List<Content> from) {
		return translateList(from, new TranslatorContent());
	}

	public static List<DatatypeContent> translateContentSummary(
			List<Content> from) {
		return translateList(from, new TranslatorContentSummary());
	}

	public static List<DatatypeUser> translateNormalUser(List<NormalUser> from) {
		return translateList(from, new TranslatorUser());
	}
	
	public static List<DatatypeNotification> translateNotification(List<Notification> from) {
		return translateList(from, new TranslatorNotification());
	}

	public static List<DatatypeTag> translateTag(List<Tag> from) {
		return translateList(from, new TranslatorTag());
	}

	public static List<DatatypeComment> translateComments(List<Comment> from) {
		return translateList(from, new TranslatorComment());
	}

	public static List<String> translateModUser(List<NormalUser> from) {
		return translateList(from, new TranslatorModUser());
	}

	public static List<DatatypeMostTagged> translateMostTagged(List<NormalUser> from) {
		return translateList(from, new TranslatorMostTagged());
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
