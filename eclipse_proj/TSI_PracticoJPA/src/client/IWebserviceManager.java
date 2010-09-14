package client;

import java.rmi.RemoteException;
import java.util.Enumeration;

import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import javax.ejb.Remote;

import bean.Webservice;

@Remote
public interface IWebserviceManager extends EJBHome{
	  public Webservice create(int ID, String name, String url) throws RemoteException;
	  //public Webservice findByPrimaryKey(TestPK key) throws RemoteException, FinderException;

}
