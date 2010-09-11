package edu.tsi.Practico.SC;

import edu.tsi.Practico.Proxy.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.rpc.ServiceException;



@WebService(name="ServicioCompuesto")
public class ServicioCompuesto {

	private static HashMap<String, Collection<String>> session_reg;
	private static int nextInt;
	
	public ServicioCompuesto() {
		session_reg = new HashMap<String,Collection<String>>();
		nextInt = 0;
	}
	
	@WebMethod
	public String inciarSesion() {
		String userID = generateUserID();
		Collection<String> col = new ArrayList<String>();
		col.add("Sesion iniciada a las " + new Date() + "\n");
		session_reg.put(userID, col);
		System.out.println("Nueva sesion, SID = " + userID);
		return userID;
	}
	
	@WebMethod
	public String finalizarSesion(@WebParam(name="SID") String userID) {
		if(session_reg.containsKey(userID)){
			Collection<String> col = session_reg.get(userID);
			col.add("Sesion finalizada a las " + new Date() + "\n");
			String logs = col.toString();
			System.out.println("Enviando logs al usuario " + userID + " que contiene:\n" + logs);
			session_reg.remove(userID);
			return logs;
		} else {
			return "SID no valido";
		}
	}
	
	@WebMethod
	public String invocarCombinacionWS1(@WebParam(name="SID") String userID) {
		
        int index = userID.indexOf('_');
        
        if(index == -1) System.err.println("NO SE MANDO WS EN EL MENSAJE");
        
        //int ID = Integer.parseInt(userID.substring(0, index));
        String ID = userID.substring(0, index);
        String userName = userID.substring(index + 1);
		
		System.out.println("Invocaron WS1 con SID = " + ID);
		if(session_reg.containsKey(ID)){			
			Collection<String> col = session_reg.get(ID);
			col.add("Se invoco al web service WS1 a las " + new Date());
			session_reg.put(ID, col);
			
			//TODO: INVOCAR COMBINACION DE WS1
			try {
				SimpleWS1Service_PortType hello = new SimpleWS1Service_ServiceLocator().getSimpleWS1ServicePort();
			    String resp = hello.invoke(userName);
			    return resp;
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}			
		return "SID no valido.";
	}
	
	@WebMethod
	public String invocarCombinacionWS2(@WebParam(name="SID") String userID) {
        int index = userID.indexOf('_');
        
        if(index == -1) System.err.println("NO SE MANDO WS EN EL MENSAJE");
        
        //int ID = Integer.parseInt(userID.substring(0, index));
        String ID = userID.substring(0, index);
        String userName = userID.substring(index + 1);
        
		System.out.println("Invocaron WS2 con SID = " + ID);
		if(session_reg.containsKey(ID)){
			Collection<String> col = session_reg.get(ID);
			col.add("Se invoco al web service WS1 a las " + new Date());
			session_reg.put(ID, col);
			try {
				//TODO: INVOCAR COMBINACION DE WS2
				SimpleWS2Service_PortType hello = new SimpleWS2Service_ServiceLocator().getSimpleWS2ServicePort();
			    String resp = hello.invoke(userName);
			    return resp;
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "SID no valido.";
	}
	
	@WebMethod
	public String invocarCombinacionWS3(@WebParam(name="SID") String userID) {
		//TODO: INVOCAR COMBINACION DE WS3
		session_reg.get(userID).add("Se invocó a la combinacion 3 a las " + new Date());
		return "Combinacion 3 invocada";
	}
	
	private String generateUserID() {
		nextInt++;
		return Integer.valueOf(nextInt).toString();
	}
	
}
