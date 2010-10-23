package partuzabook.entityTranslators;

import partuzabook.datatypes.DatatypeContent;

public class TranslatorMostCommented implements ITranslatable {
	
	public Object translate(Object iEnt) {
		Object[] ent = (Object[])iEnt;
		DatatypeContent dat = new DatatypeContent();
		dat.setContId((Integer)ent[0]);
		return dat;
	}
}
