package manager;

import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import javax.ejb.Local;

import bean.Cache;

@Local
public interface ICacheManager {
	  public Cache create(String params, String result, int idws) throws Exception;
	  public Cache findByParamsAndIdws(String params, int idws) throws FinderException;
	  public void remove(Cache c);
}