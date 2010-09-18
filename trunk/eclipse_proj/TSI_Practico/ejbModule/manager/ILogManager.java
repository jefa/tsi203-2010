package manager;

import java.rmi.RemoteException;
import java.util.Date;

import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import javax.ejb.Local;

import bean.Log;
import bean.LogPK;

@Local
public interface ILogManager extends EJBHome{
	  public Log create(int idws, Date date, String outcome);
	  public Log findByPrimaryKey(LogPK key) throws FinderException;

}