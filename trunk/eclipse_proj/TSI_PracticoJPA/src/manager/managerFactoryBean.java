package manager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class managerFactoryBean implements managerFactoryLocal{

	@PersistenceContext
	EntityManager em;

	public ICacheManager getICacheManager() {
		// TODO Auto-generated method stub
		ICacheManager icm = new CacheManager(em);
		return null;
	}

	public ILogManager getILogManager() {
		// TODO Auto-generated method stub
		return null;
	}

	public IWebserviceManager getIWebserviceManager() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
