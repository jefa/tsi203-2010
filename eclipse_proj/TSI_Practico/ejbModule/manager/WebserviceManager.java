package manager;

import java.rmi.RemoteException;

import javax.ejb.EJBMetaData;
import javax.ejb.Handle;
import javax.ejb.HomeHandle;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import bean.Webservice;

@Stateless 
public class WebserviceManager implements IWebserviceManager {

	@PersistenceContext
	private EntityManager em;
		
	public WebserviceManager() {

	}
	
	public WebserviceManager( EntityManager entityManager) {
		em = entityManager;
	}
	
	public Webservice create(String name, String url){
		
		Webservice ws = new Webservice();
		
		int size = em.createQuery("SELECT w FROM Webservice w").getResultList().size();
		
		ws.setName(name);
		ws.setUrl(url);
		ws.setId(size);
		
		try {
			em.persist(ws);
		} catch(Exception e) {
			e.printStackTrace();
		} 
		return ws;
	}

}