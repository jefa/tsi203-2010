package partuzabook.integracion.ws.translators;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.integracion.ws.contenido.Contenido_Type;

public class TranslatorContenido_TypeDatatypeContent implements ITranslatable {
	
	public Object translate(Object iEnt) {
		Contenido_Type contenidoType = (Contenido_Type)iEnt;
		DatatypeContent dat = new DatatypeContent();
		dat.setUrl(contenidoType.getUrl());
		dat.setDescription(contenidoType.getDescripcion());
		dat.setType(contenidoType.getUrl().contains("youtube") ?
			DatatypeContent.EXTERNAL_VIDEO : DatatypeContent.EXTERNAL_PHOTO);
		return dat;
	}
}
