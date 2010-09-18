package manager;

import java.rmi.RemoteException;

import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import javax.ejb.Remote;

import bean.Cache;
import bean.Webservice;

@Remote
public interface ICacheManager extends EJBHome{
	  public Cache create(String params, String result, int idws) throws RemoteException;
	  public Cache findByParamsAndIdws(String params, int idws) throws RemoteException, FinderException;
}