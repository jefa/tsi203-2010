package manager;

import java.rmi.RemoteException;

import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import javax.ejb.Local;

import bean.Cache;

@Local
public interface ICacheManager extends EJBHome{
	  public Cache create(String params, String result, int idws) throws Exception;
	  public Cache findByParamsAndIdws(String params, int idws) throws FinderException;
}