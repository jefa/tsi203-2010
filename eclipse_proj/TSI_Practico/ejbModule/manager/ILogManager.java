package manager;

import java.util.Date;

import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import javax.ejb.Local;

import org.jboss.remoting.samples.chat.exceptions.InvalidArgumentException;

import bean.Log;
import bean.LogPK;

@Local
public interface ILogManager extends EJBHome{
	  public Log create(int idws, Date date, String outcome) throws Exception;
	  public Log findByPrimaryKey(LogPK key) throws FinderException;

}