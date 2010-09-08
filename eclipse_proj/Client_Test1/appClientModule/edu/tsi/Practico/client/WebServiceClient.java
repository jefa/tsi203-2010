package edu.tsi.Practico.client;

import javax.xml.ws.WebServiceRef;

import edu.tsi.Practico.TestWSService_PortType;
import edu.tsi.Practico.TestWSService_Service;
import javax.xml.rpc.ServiceException;


public class WebServiceClient {

    @WebServiceRef(wsdlLocation = "http://127.0.0.1:8080/WebProject/TestWS?wsdl")
    static TestWSService_Service service;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

        try { // Call Web Service Operation
            System.out.println(
                    "Retrieving the port from the following service: "
                    + service);
            
            TestWSService_PortType port = service.getTestWSServicePort();

            System.out.println("Invoking the sayHello operation on the port.");

            String name;

            if (args.length > 0) {
                name = args[0];
            } else {
                name = "No Name";
            }

            String response = port.greet(name);
            System.out.println(response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


	}

}
