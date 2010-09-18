package manager;

import java.util.Date;

import javax.ejb.FinderException;
import javax.ejb.Local;

import bean.Cache;

@Local
public interface ICacheManager {
	  public Cache create(String params, String result, int idws, Date reg_date) throws Exception;
	  public Cache findByParamsAndIdws(String params, int idws) throws FinderException;
	  public void update(Cache c, String result, Date reg_date);
}