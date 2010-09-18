package manager;

import java.rmi.RemoteException;

import javax.ejb.EJBHome;
import javax.ejb.Local;

import bean.Webservice;

@Local
public interface IWebserviceManager extends EJBHome{
	
	/**
	 * Crea un nuevo objeto Webservice en la base con nombre name y direccion url. El ID se asigna automáticamente
	 * @param name Nombre del nuevo Webservice
	 * @param url Direccion web del nuevo Webserice
	 * @return El nuevo Webservice
	 * */
	public Webservice create(String name, String url);
	  //public Webservice findByPrimaryKey(TestPK key) throws RemoteException, FinderException;

}
