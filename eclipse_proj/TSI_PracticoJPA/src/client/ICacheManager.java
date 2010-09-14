package client;

import java.rmi.RemoteException;
import java.util.Enumeration;

import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import javax.ejb.Remote;

import bean.Cache;

@Remote
public interface ICacheManager extends EJBHome{
	  public Cache create(int ID, String params, String result) throws RemoteException;
	  //public Cache findByPrimaryKey(CachePK key) throws RemoteException, FinderException;
}