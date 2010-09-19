package edu.tsi.Practico.SC;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.rpc.ServiceException;

import manager.ICacheManager;
import manager.managerFactoryBean;
import bean.Cache;

import com.cdyne.ws.WeatherWS.Forecast;
import com.cdyne.ws.WeatherWS.ForecastReturn;
import com.cdyne.ws.WeatherWS.WeatherLocator;
import com.cdyne.ws.WeatherWS.WeatherSoap;

import edu.tsi.Practico.Proxy.SimpleWS1Service_PortType;
import edu.tsi.Practico.Proxy.SimpleWS1Service_ServiceLocator;
import edu.tsi.Practico.Proxy.SimpleWS2Service_PortType;
import edu.tsi.Practico.Proxy.SimpleWS2Service_ServiceLocator;

@WebService(name="ServicioCompuesto")
public class ServicioCompuesto {

	private static HashMap<String, Collection<String>> session_reg;
	private static int nextInt;
	
	//Cantidad de segundos que es válido un cache.
	private static final long msecForValidCache = 60000;
	
	private static managerFactoryBean managerFactory;
		
	public ServicioCompuesto() {
		session_reg = new HashMap<String,Collection<String>>();
		nextInt = 0;
		managerFactory = new managerFactoryBean();
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
		//FIXME Esta mal tenerlo hardcodeado
		int idws = 1;
		
        int index = userID.indexOf('_');
        
        if(index == -1) {
        	System.err.println("NO SE MANDO WS EN EL MENSAJE");
        }
        
        String ID = userID.substring(0, index);
        String userName = userID.substring(index + 1);
		
		System.out.println("Invocaron WS1 con SID = " + ID);
    	this.escribirEnLog(idws, "Invocaron WS1 con SID = " + ID);
    	
		if(session_reg.containsKey(ID)){			
			Collection<String> col = session_reg.get(ID);
			String msg = "Se invoco al web service WS1 a las " + new Date();
			col.add(msg);
			session_reg.put(ID, col);
	    	this.escribirEnLog(idws, msg);
			
			try {
				String resp = this.resultadoDeCache(idws, userName);
				if(resp == null) {
					//No tengo cache
					SimpleWS1Service_PortType hello = new SimpleWS1Service_ServiceLocator().getSimpleWS1ServicePort();
				    resp = hello.invoke(userName);
				    this.guardarEnCache(idws, userName, resp);
				}
				this.escribirEnLog(idws, "Respuesta para " + ID + ": " + resp);
			    return resp;
			} catch (ServiceException e) {
				e.printStackTrace();
				String errMsg = "ERROR (ServiceException) al obtener la respuesta del servidor WS1 para el userName="+userName;
				this.escribirEnLog(idws, errMsg);
				return errMsg;
			} catch (RemoteException e) {
				e.printStackTrace();
				String errMsg = "ERROR (RemoteException)al obtener la respuesta del servidor WS1 para el userName="+userName;
				this.escribirEnLog(idws, errMsg);
				return errMsg;
			}
		}
		this.escribirEnLog(idws, "SID no valido");
		return "SID no valido";
	}
	
	@WebMethod
	public String invocarCombinacionWS2(@WebParam(name="SID") String userID) {
		//FIXME Esta mal tenerlo hardcodeado
		int idws = 2;
		
        int index = userID.indexOf('_');
        
        if(index == -1) System.err.println("NO SE MANDO WS EN EL MENSAJE");
        
        //int ID = Integer.parseInt(userID.substring(0, index));
        String ID = userID.substring(0, index);
        String userName = userID.substring(index + 1);
        
        System.out.println("Invocaron WS2 con SID = " + ID);
    	this.escribirEnLog(idws, "Invocaron WS2 con SID = " + ID);
    	
		if(session_reg.containsKey(ID)){
			Collection<String> col = session_reg.get(ID);
			String msg = "Se invoco al web service WS2 a las " + new Date();
			col.add(msg);
			session_reg.put(ID, col);
	    	this.escribirEnLog(idws, msg);	    	
	    	
			try {
				String resp = this.resultadoDeCache(idws, userName);
				if(resp == null) {
					//No tengo cache
					SimpleWS2Service_PortType hello = new SimpleWS2Service_ServiceLocator().getSimpleWS2ServicePort();
				    resp = hello.invoke(userName);
				    this.guardarEnCache(idws, userName, resp);
				}
				this.escribirEnLog(idws, "Respuesta para " + ID + ": " + resp);
			    return resp;
			} catch (ServiceException e) {
				e.printStackTrace();
				String errMsg = "ERROR (ServiceException) al obtener la respuesta del servidor WS2 para el userName="+userName;
				this.escribirEnLog(idws, errMsg);
				return errMsg;
			} catch (RemoteException e) {
				e.printStackTrace();
				String errMsg = "ERROR (RemoteException)al obtener la respuesta del servidor WS2 para el userName="+userName;
				this.escribirEnLog(idws, errMsg);
				return errMsg;
			}
		}
		this.escribirEnLog(idws, "SID no valido");
		return "SID no valido.";
	}
	
	@WebMethod
	public String invocarCombinacionWS3(@WebParam(name="SID") String zipCode) {
		//FIXME Esta mal tenerlo hardcodeado
		int idws = 3;
		
        int index = zipCode.indexOf('_');
        if(index == -1) {
        	System.err.println("NO SE MANDO WS EN EL MENSAJE");
        	this.escribirEnLog(idws, "NO SE MANDO WS EN EL MENSAJE");
        }
        
        String ID = zipCode.substring(0, index);
        String param = zipCode.substring(index + 1);

        int index2 = param.indexOf('_');
        if(index2 == -1) {
        	System.err.println("NO SE MANDO WS EN EL MENSAJE");
        	this.escribirEnLog(idws, "NO SE MANDO WS EN EL MENSAJE");
        }

        String user = param.substring(0,index2);
        String zip = param.substring(index2 + 1);
        
        System.out.println("Invocaron WS3 con SID = " + ID);
        this.escribirEnLog(idws, "Invocaron WS3 con SID = " + ID);
        
		if(session_reg.containsKey(ID)){
			Collection<String> col = session_reg.get(ID);
			String msg = "Se invoco al web service WS3 a las " + new Date();
			col.add(msg);
			session_reg.put(ID, col);
	    	this.escribirEnLog(idws, msg);

			try {
				// Invocar al SimpleWS1 con parametro username
			    String respWS1 = this.resultadoDeCache(idws, user);
				if(respWS1 == null) {
					//No tengo cache
					SimpleWS1Service_PortType hello = new SimpleWS1Service_ServiceLocator().getSimpleWS1ServicePort();
					respWS1 = hello.invoke(user);
				    this.guardarEnCache(idws, user, respWS1);
				}
				
				// Invocar al Web Service del Weather (externo), con el zipcode
				String respWSWeather = this.resultadoDeCache(idws, zip);
				if(respWSWeather == null) {
					//No tengo cache
					WeatherLocator loc = new WeatherLocator();
					WeatherSoap w = loc.getWeatherSoap();
					ForecastReturn ret = w.getCityForecastByZIP(zip);	
					Forecast[] res = ret.getForecastResult();
					Forecast f = res[0];
					respWSWeather = "El zip code ingresado corresponde a la ciudad de " + ret.getCity()+ ". La temperatura pronosticada es de: " + f.getTemperatures().getDaytimeHigh() + " grados";				
					this.guardarEnCache(idws, user, respWSWeather);
				}				

				this.escribirEnLog(idws, "Respuesta para " + ID + ": " +  respWS1 + " " + respWSWeather);
				return respWS1 + " " + respWSWeather;

			} catch (RemoteException e) {
				e.printStackTrace();
				String errMsg = "ERROR (RemoteException)al obtener la respuesta del servidor WS3 para el zipCode="+zip;
				this.escribirEnLog(idws, errMsg);
				return errMsg;
			} catch (ServiceException e) {
				e.printStackTrace();
				String errMsg = "ERROR (ServiceException) al obtener la respuesta del servidor WS2 para el zipCode="+zip;
				this.escribirEnLog(idws, errMsg);
				//return errMsg;
			}
		}
		this.escribirEnLog(idws, "SID no valido.");
		return "SID no valido.";
	}
	
	private String generateUserID() {
		nextInt++;
		return Integer.valueOf(nextInt).toString();
	}
	
	private void escribirEnLog(int idws, String outcome) {
		try {
			Date date = new Date();
			managerFactory.getILogManager().create(idws, date, outcome);			
		} catch(Exception e) {
			System.out.println("Error al escribir en log: " + idws + " - " + outcome);
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Guarda en cache el resultado de invocar al Webservice idws, con los parametros params
	 * @param idws ID del Webservice que se invoco
	 * @param params Parametros con los que se invoco al Webservice idws
	 * @param result Resultado que devuelve el Webservice idws, al ser ejecutado con los parametros params
	 * */
	private void guardarEnCache(int idws, String params, String result) {
		try {
			ICacheManager icm = managerFactory.getICacheManager();
			Cache c = icm.findByParamsAndIdws(params, idws);
			if( c != null) {
				//Update
				icm.update(c, result, new Date());
			} else {
				//Create
				icm.create(params, result, idws, new Date());
			}
		} catch (Exception e) {
			System.out.println("No se pudo realizar cache para el idws = " + idws);
			e.printStackTrace();
		}
	}
	
	/**
	 * Devuelve lo que haya en la cache, segun los parametros con los que se invoca. Si la cache es vieja, la borra.
	 * @param idws ID del Webservice
	 * @param params Parametros con los que se invocara al Webservice idws
	 * @return null en caso de que no haya cache, o se haya limpiado la misma. El resultado que se grabo en cache en caso contrario
	 * */
	private String resultadoDeCache(int idws, String params) {
		String res = null;
		try {
			Cache c = managerFactory.getICacheManager().findByParamsAndIdws(params, idws);			
			if(c != null) {
				//Verifico que sirva el cache, sino lo desecho
				if(cacheAlDia(c.getReg_date(), new Date(), ServicioCompuesto.msecForValidCache)) {
					res = c.getResult();
				}
			}
		} catch(Exception e){
			System.out.println("Error al obtener cache");
			System.out.println(e.getMessage());
		}
		return res;
	}
	
	private boolean cacheAlDia(Date d_en_cache, Date d_a_comparar, long msec) {
		return ((d_en_cache.getTime() + msec) > d_a_comparar.getTime());
		
	}
}
