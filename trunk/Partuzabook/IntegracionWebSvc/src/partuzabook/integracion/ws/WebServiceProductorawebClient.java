package partuzabook.integracion.ws;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import partuzabook.integracion.ws.productora_web.IntegracionWSService;
import partuzabook.integracion.ws.productora_web.IntegracionWSServicePortType;


public class WebServiceProductorawebClient {

	public static void main(String[] args) {
		
		try {
			Service service = Service.create(
					new URL("http://192.168.1.126:8080/Productora_web/services/IntegracionWSService?wsdl"),
					new QName("{http://ejb.integracion.tsi2.fing.edu.uy}IntegracionWSService", "IntegracionWSService")
					
			);
			IntegracionWSServicePortType ws = service.getPort(IntegracionWSServicePortType.class);
			//IntegracionWSServicePortType ws = new IntegracionWSService().getPort(IntegracionWSServicePortType.class);
			
			Object resutl = ws.searchEventByName("");			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
