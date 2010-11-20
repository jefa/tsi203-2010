package partuzabook.integracion.ws;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.Holder;
import javax.xml.ws.Service;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import partuzabook.integracion.ws.busqueda.Busqueda;
import partuzabook.integracion.ws.busqueda.BusquedaService;
import partuzabook.integracion.ws.busqueda.Evento;

public class WebServiceClient {

	public static void main(String[] args) {
		
		try {
			Service service = Service.create(
					new URL("http://127.0.0.1:8080/PartuzabookEAR-IntegracionWebSvc/BusquedaBean?wsdl"),
					new QName("http://edu.tsi2.ws/integracion/ws/busqueda", "BusquedaService")
					
			);
			Busqueda busqueda = service.getPort(Busqueda.class);
			//Busqueda busqueda = new BusquedaService().getBusquedaPort();
			
			Holder<List<Evento>> c = new Holder<List<Evento>>();
			c.value = new ArrayList<Evento>();
			Holder<Integer> tot = new Holder<Integer>();
			tot.value = new Integer(0);
			busqueda.searchByName("", 100, c, tot);
			System.out.println("TOTAL ES:::  " + tot.value);
			List<Evento> result = c.value;
			for (Evento evento : result) {
				System.out.println("Evento encontrado:::  " + evento.getNombre());
			}
			
			c.value = new ArrayList<Evento>();
			tot.value = new Integer(0);
			XMLGregorianCalendarImpl fechaFrom = new XMLGregorianCalendarImpl();
			fechaFrom.setYear(2011);
			fechaFrom.setMonth(10);
			fechaFrom.setDay(1);
			fechaFrom.setHour(0);
			fechaFrom.setMinute(0);
			fechaFrom.setSecond(0);
			
			XMLGregorianCalendarImpl fechaTo = new XMLGregorianCalendarImpl();
			fechaTo.setYear(2011);
			fechaTo.setMonth(10);
			fechaTo.setDay(31);
			fechaTo.setHour(0);
			fechaTo.setMinute(0);
			fechaTo.setSecond(0);
			busqueda.searchBetweenDate(fechaFrom, fechaTo, 100, c, tot);
			System.out.println("TOTAL ES:::  " + tot.value);
			result = c.value;
			for (Evento evento : result) {
				System.out.println("Evento encontrado:::  " + evento.getNombre());
			}

			// do something with the service stub here...
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
