package partuzabook.integracion.ws.proxy;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.integracion.ws.productora_web.DataContent;
import partuzabook.integracion.ws.productora_web.DataEvent;
import partuzabook.integracion.ws.productora_web.IntegracionWSServicePortType;
import partuzabook.integracion.ws.translators.TranslatorCollection;
import partuzabook.utils.Parameters;

/**
 * Session Bean implementation class WebServiceProductoraExterno
 */
@Stateless
public class WebServiceProductoraExternoProductoraWeb implements WebServiceProductoraExternoRemote {

    /**
     * Default constructor. 
     */
    public WebServiceProductoraExternoProductoraWeb() {
        // TODO Auto-generated constructor stub
    }

    private IntegracionWSServicePortType getService() {
		try {
			Service service = Service.create(
				new URL("http://" + Parameters.PRODUCTORA_WEB_IP + "/PruebaIntegracion/services/IntegracionWSService?wsdl"),
				new QName("http://ws.integracion.tsi2.fing.edu.uy", "IntegracionWSService")
			);
			return service.getPort(IntegracionWSServicePortType.class);
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
    }
    
	@Override
	public List<DatatypeEventSummary> searchEventByName(String name) {
		IntegracionWSServicePortType service = getService();
		return TranslatorCollection.translateFromDataEvent(service.searchEventByName(name));
	}

	@Override
	public List<DatatypeEventSummary> searchEventByDate(Date date) {
		IntegracionWSServicePortType service = getService();
		XMLGregorianCalendar g = new XMLGregorianCalendarImpl();
		g.setMillisecond((int) date.getTime());
		return TranslatorCollection.translateFromDataEvent(service.searchEventByDate(g));
	}

	@Override
	public List<DatatypeEventSummary> searchEventBetweenDates(Date before,
			Date after) {
		IntegracionWSServicePortType service = getService();
		XMLGregorianCalendar g = new XMLGregorianCalendarImpl();
		g.setMillisecond((int) before.getTime());
		return TranslatorCollection.translateFromDataEvent(service.searchEventByDate(g));
	}

	@Override
	public List<DatatypeEventSummary> searchEventByType(Integer type) {
		IntegracionWSServicePortType service = getService();
		return TranslatorCollection.translateFromDataEvent(service.searchEventByType(type));
	}

	@Override
	public List<DatatypeContent> getAlbumContents(String eventID) {
		IntegracionWSServicePortType service = getService();
		return TranslatorCollection.translateFromDataContent(service.getAlbumContents(eventID));
	}

}
