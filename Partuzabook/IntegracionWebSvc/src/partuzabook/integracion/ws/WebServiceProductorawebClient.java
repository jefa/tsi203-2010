package partuzabook.integracion.ws;

import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import partuzabook.integracion.ws.productora_web.DataEvent;
import partuzabook.integracion.ws.productora_web.IntegracionWSServicePortType;

public class WebServiceProductorawebClient {

	public static void main(String[] args) {
		
		try {
			Service service = Service.create(
				new URL("http://192.168.1.101:8180/PruebaIntegracion/services/IntegracionWSService?wsdl"),
				new QName("http://ws.integracion.tsi2.fing.edu.uy", "IntegracionWSService")
			);
			IntegracionWSServicePortType ws = service.getPort(IntegracionWSServicePortType.class);
			//IntegracionWSServicePortType ws = new IntegracionWSService().getPort(IntegracionWSServicePortType.class);
			
			List<DataEvent> resutl = ws.searchEventByName("");
			
			System.out.println("RESULT:::: "+resutl.size());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
