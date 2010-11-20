package partuzabook.integracion;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Holder;
import javax.xml.ws.Service;

import partuzabook.integracion.ws.Busqueda;
import partuzabook.integracion.ws.BusquedaService;
import partuzabook.integracion.ws.Evento;;

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
			
			System.out.println("DESCUENTO ES:::  " + tot.value);

			// do something with the service stub here...
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
