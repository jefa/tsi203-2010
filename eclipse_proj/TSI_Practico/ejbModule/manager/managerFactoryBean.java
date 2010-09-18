package manager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import session.TestBean;
import session.TestLocal;

@Stateless
public class managerFactoryBean implements managerFactoryLocal{

	@PersistenceContext(unitName="TSI_Practico")
	private EntityManager em;
	
	private static CacheManager cm;
	private static LogManager lm;
	private static WebserviceManager wm;

	public ICacheManager getICacheManager() {
		if (cm == null)  cm = new CacheManager(em);
		return cm;
	}

	public ILogManager getILogManager() {
		if (lm == null) lm = new LogManager(em);
		return lm;
	}

	public IWebserviceManager getIWebserviceManager() {
		TestLocal t = new TestBean();
		if (wm == null) wm = new WebserviceManager(em);
		return wm;
	}
	
}
