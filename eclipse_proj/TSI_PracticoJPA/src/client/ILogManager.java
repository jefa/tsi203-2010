package client;

import java.rmi.RemoteException;
import java.util.Enumeration;

import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import javax.ejb.Remote;

import bean.Log;
import bean.LogPK;

@Remote
public interface ILogManager extends EJBHome{
	  public Log create(int ID, String TEXT) throws RemoteException;
	  public Log findByPrimaryKey(LogPK key) throws RemoteException, FinderException;

}