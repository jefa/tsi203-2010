package partuzabook.integracion.ws.proxy;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.integracion.ws.productora_web.DataContent;
import partuzabook.integracion.ws.productora_web.DataEvent;

@Remote
public interface WebServiceProductoraExternoRemote {
	public List<DatatypeEventSummary> searchEventByName(String name);

	public List<DatatypeEventSummary> searchEventByDate(Date date);

	public List<DatatypeEventSummary> searchEventBetweenDates(Date before, Date after);

	public List<DatatypeEventSummary> searchEventByType(Integer type);
	
	public List<DatatypeContent> getAlbumContents(String eventID);

}
