package partuzabook.entityTranslators;

import partuzabook.datatypes.DatatypeContent;

public class TranslatorBestRanked implements ITranslatable {
	
	public Object translate(Object iEnt) {
		Object[] ent = (Object[])iEnt;
		DatatypeContent dat = new DatatypeContent();
		dat.contId = (Integer)ent[0];
		dat.avgScore = (Double)ent[1];
		return dat;
	}
}
