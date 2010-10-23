package partuzabook.entityTranslators;

import partuzabook.datatypes.DatatypeTag;
import partuzabook.datos.persistencia.beans.Tag;
import partuzabook.datos.persistencia.beans.TagForNotUser;
import partuzabook.datos.persistencia.beans.TagForUser;

public class TranslatorTag implements ITranslatable {
	
	public Object translate(Object iEnt) {
		Tag tag = (Tag)iEnt;
		DatatypeTag dat = new DatatypeTag();
		dat.setContentId(tag.getContent().getCntIdAuto());
		dat.setPosX(tag.get_posX_());
		dat.setPosY(tag.get_posY_());
		dat.setUserName("");
		// Translate name of user tagged, depending if tag is TagForUser or not
		if (tag instanceof TagForNotUser) {
			TagForNotUser tagNU = (TagForNotUser) tag;
			dat.setName(tagNU.getUsrTagCustom());
			dat.setIsRealUser(false);
		}
		else if (tag instanceof TagForUser) {
			TagForUser tagU = (TagForUser) tag;
			dat.setUserName(tagU.getUserTagged().getUsername());
			dat.setName(tagU.getUserTagged().getName());
			dat.setIsRealUser(true);
		} 
		return dat;
	}
}
