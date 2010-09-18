package manager;

import java.util.Date;

import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import javax.ejb.Local;

import bean.Log;
import bean.LogPK;

@Local
public interface ILogManager {
	  public Log create(int idws, Date date, String outcome) throws Exception;
	  public Log findByPrimaryKey(LogPK key) throws FinderException;

}