package edu.tsi.Practico.client;

import javax.xml.ws.WebServiceRef;

import edu.tsi.Practico.Proxy.SimpleWS1Service_PortType;
import edu.tsi.Practico.Proxy.SimpleWS1Service_Service;


public class WebServiceClient {

    @WebServiceRef(wsdlLocation = "http://127.0.0.1:8080/WebProject/SimpleWS1?wsdl")
    static SimpleWS1Service_Service service;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

        try { // Call Web Service Operation
            System.out.println(
                    "Retrieving the port from the following service: "
                    + service);
            
            SimpleWS1Service_PortType port = service.getSimpleWS1ServicePort();

            System.out.println("Invoking the sayHello operation on the port.");

            String name;

            if (args.length > 0) {
                name = args[0];
            } else {
                name = "No Name";
            }

            String response = port.invoke(name);
            System.out.println(response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


	}

}
