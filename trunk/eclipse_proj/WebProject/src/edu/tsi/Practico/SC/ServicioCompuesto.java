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

import com.cdyne.ws.WeatherWS.ForecastReturn;
import com.cdyne.ws.WeatherWS.Forecast;
import com.cdyne.ws.WeatherWS.WeatherLocator;
import com.cdyne.ws.WeatherWS.WeatherSoap;
import com.cdyne.ws.WeatherWS.WeatherSoapProxy;



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
			
			//TODO: INVOCAR COMBINACION DE WS 1
			try {
				SimpleWS1Service_PortType hello = new SimpleWS1Service_ServiceLocator().getSimpleWS1ServicePort();
			    String resp = hello.invoke(userName);
			    return resp;
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "ERROR (ServiceException) al obtener la respuesta del servidor WS1 para el userName="+userName;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "ERROR (RemoteException)al obtener la respuesta del servidor WS1 para el userName="+userName;
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
			col.add("Se invoco al web service WS2 a las " + new Date());
			session_reg.put(ID, col);
			try {
				//TODO: INVOCAR COMBINACION DE WS2
				SimpleWS2Service_PortType hello = new SimpleWS2Service_ServiceLocator().getSimpleWS2ServicePort();
			    String resp = hello.invoke(userName);
			    return resp;
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "ERROR (ServiceException) al obtener la respuesta del servidor WS1 para el userName="+userName;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "ERROR (RemoteException)al obtener la respuesta del servidor WS1 para el userName="+userName;
			}
		}
		return "SID no valido.";
	}
	
	@WebMethod
	public String invocarCombinacionWS3(@WebParam(name="SID") String zipCode) {
        int index = zipCode.indexOf('_');
        if(index == -1) System.err.println("NO SE MANDO WS EN EL MENSAJE");
        
        String ID = zipCode.substring(0, index);
        String param = zipCode.substring(index + 1);

        int index2 = param.indexOf('_');
        if(index2 == -1) System.err.println("NO SE MANDO WS EN EL MENSAJE");

        String user = param.substring(0,index2);
        String zip = param.substring(index2 + 1);
        
        System.out.println("Invocaron WS3 con SID = " + ID);
		if(session_reg.containsKey(ID)){
			Collection<String> col = session_reg.get(ID);
			col.add("Se invoco al web service WS3 a las " + new Date());
			session_reg.put(ID, col);
			try {
				// Invocar al SimpleWS1 con parametro username
				SimpleWS1Service_PortType hello = new SimpleWS1Service_ServiceLocator().getSimpleWS1ServicePort();
			    String respWS1 = hello.invoke(user);
				
				// Invocar al Web Service del Weather (externo), con el zipcode
				WeatherLocator loc = new WeatherLocator();
				WeatherSoap w = loc.getWeatherSoap();
				ForecastReturn ret = w.getCityForecastByZIP(zip);	
				Forecast[] res = ret.getForecastResult();
				Forecast f = res[0];
				String respWSWeather = "El zip code ingresado corresponde a la ciudad de " + ret.getCity()+ ". La temperatura pronosticada es de: " + f.getTemperatures().getDaytimeHigh() + " grados";				

				return respWS1 + " " + respWSWeather;

			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "ERROR (RemoteException)al obtener la respuesta del servidor WS3 para el zipCode="+zip;
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "SID no valido.";
	
	}
	
	private String generateUserID() {
		nextInt++;
		return Integer.valueOf(nextInt).toString();
	}
	
}
