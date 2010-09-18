package manager;

import java.rmi.RemoteException;
import java.util.Date;

import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import javax.ejb.Remote;

import bean.Log;
import bean.LogPK;
import bean.Webservice;

@Remote
public interface ILogManager extends EJBHome{
	  public Log create(int idws, Date date, String outcome) throws RemoteException;
	  public Log findByPrimaryKey(LogPK key) throws RemoteException, FinderException;

}