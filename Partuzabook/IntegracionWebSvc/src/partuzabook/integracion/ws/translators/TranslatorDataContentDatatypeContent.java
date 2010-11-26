package partuzabook.integracion.ws.translators;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.integracion.ws.productora_web.DataContent;

public class TranslatorDataContentDatatypeContent implements ITranslatable {
	
	public Object translate(Object iEnt) {
		DataContent dataContent = (DataContent)iEnt;
		DatatypeContent dat = new DatatypeContent();
		dat.setUrl(dataContent.getUrlPublica().getValue());
		dat.setDescription(dataContent.getDescripcion().getValue());
		return dat;
	}
}
