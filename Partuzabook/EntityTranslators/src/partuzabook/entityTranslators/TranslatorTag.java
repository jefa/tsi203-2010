package partuzabook.entityTranslators;

import partuzabook.datatypes.DatatypeTag;
import partuzabook.datos.persistencia.beans.Tag;
import partuzabook.datos.persistencia.beans.TagForNotUser;
import partuzabook.datos.persistencia.beans.TagForUser;

public class TranslatorTag implements ITranslatable {
	
	public Object translate(Object iEnt) {
		Tag tag = (Tag)iEnt;
		DatatypeTag dat = new DatatypeTag();
		dat.contentId = tag.getContent().getCntIdAuto();
		dat.posX = tag.get_posX_();
		dat.posY = tag.get_posY_();
		dat.userName = "";
		// Translate name of user tagged, depending if tag is TagForUser or not
		if (tag instanceof TagForNotUser) {
			TagForNotUser tagNU = (TagForNotUser) tag;
			dat.userName = tagNU.getUsrTagCustom();
		} else if (tag instanceof TagForUser) {
			TagForUser tagU = (TagForUser) tag;
			dat.userName = tagU.getUserTagged().getName();
		} 
		return dat;
	}
}
