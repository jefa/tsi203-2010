package partuzabook.entityTranslators;

import partuzabook.datatypes.DatatypeComment;
import partuzabook.datos.persistencia.beans.Comment;

public class TranslatorComment implements ITranslatable {
	
	public Object translate(Object iEnt) {
		Comment com = (Comment)iEnt;
		DatatypeComment dat = new DatatypeComment();
		dat.contentId = com.getContent().getCntIdAuto();
		dat.text = com.getText();
		dat.userName = com.getUser().getName();
		return dat;
	}
}
