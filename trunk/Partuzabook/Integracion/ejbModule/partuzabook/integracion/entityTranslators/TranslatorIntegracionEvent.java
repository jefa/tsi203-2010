package partuzabook.integracion.entityTranslators;

import partuzabook.datos.persistencia.beans.Event;
import partuzabook.integracion.datatypes.DatatypeEvento;
import partuzabook.integracion.entityTranslators.ITranslatable;

public class TranslatorIntegracionEvent implements ITranslatable {
	
	public Object translate(Object iEnt) {
		Event ent = (Event)iEnt;
		DatatypeEvento dat = new DatatypeEvento();
		dat.setIdEvento(ent.getEvtIdAuto());
		dat.setNombre(ent.getEvtName());
		int type = 3;
		// Setear tipo de evento
		String categ = ent.getEvtCategory().getCategory();
		if (categ.equals("Aniversario")){
			type = 0;
		} else if (categ.equals("Casamiento")){
			type = 1;
		} else if (categ.equals("Cumpleaños de quince")){
			type = 2;
		} 
		dat.setTipo(type);
		dat.setFecha(ent.getDate());
		dat.setDescripcion(ent.getDescription());
		dat.setDireccion(ent.getAddress());
		// Setear imagen de portada del evento, si es que se tiene. 
		//	Sino, va null o String vacío
		String cover = "UsuarioUI/ContentFeeder?id=";
		if (ent.getCover() != null) {
			dat.setUrlCover(cover + "ent.getCover().getCntIdAuto()");
		} else {
			dat.setUrlCover(null);
		}
		
		return dat;
	}
}
