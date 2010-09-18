package manager;

import java.rmi.RemoteException;
import java.util.Enumeration;

import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import javax.ejb.Remote;

import bean.Webservice;

@Remote
public interface IWebserviceManager extends EJBHome{
	
	/**
	 * Crea un nuevo objeto Webservice en la base con nombre name y direccion url. El ID se asigna automáticamente
	 * @param name Nombre del nuevo Webservice
	 * @param url Direccion web del nuevo Webserice
	 * @return El nuevo Webservice
	 * */
	public Webservice create(String name, String url) throws RemoteException;
	  //public Webservice findByPrimaryKey(TestPK key) throws RemoteException, FinderException;

}
