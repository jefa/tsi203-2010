package partuzabook.usuarioUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;

public class EventoMB {

	private DatatypeEventSummary evento;

	public DatatypeEventSummary getEvento() {
		return evento;
	}

	public void setEvento(DatatypeEventSummary evento) {
		this.evento = evento;
	}
	

}