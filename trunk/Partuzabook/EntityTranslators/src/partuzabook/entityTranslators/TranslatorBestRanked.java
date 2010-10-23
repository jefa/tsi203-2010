package partuzabook.entityTranslators;

import partuzabook.datatypes.DatatypeContent;

public class TranslatorBestRanked implements ITranslatable {
	
	public Object translate(Object iEnt) {
		Object[] ent = (Object[])iEnt;
		DatatypeContent dat = new DatatypeContent();
		dat.setContId((Integer)ent[0]);
		dat.setAvgScore((Double)ent[1]);
		return dat;
	}
}
