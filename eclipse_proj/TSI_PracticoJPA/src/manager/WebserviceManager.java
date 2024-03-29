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

	//@PersistenceContext(unitName="TSI_PracticoJPA", name="PostgresDS")
	
	//@PersistenceContext(unitName="TEST_JPA_3", type=PersistenceContextType.TRANSACTION)
	//@PersistenceContext(unitName="TEST_JPA_3")
	private EntityManager em;
	
	//@PersistenceUnit(unitName="TEST_JPA_3")
	//private EntityManagerFactory emf;
	
	public WebserviceManager( EntityManager entityManager) {
		em = entityManager;
	}
	
	public EJBMetaData getEJBMetaData() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public HomeHandle getHomeHandle() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public void remove(Handle arg0) throws RemoteException, RemoveException {
		// TODO Auto-generated method stub

	}

	public void remove(Object arg0) throws RemoteException, RemoveException {
		if( arg0 instanceof Webservice) {
			em.remove(arg0);
		}
	}

	public Webservice create(String name, String url) throws RemoteException {
		
		Webservice ws = new Webservice();
		
		//El ID se asigna autmáticamente, ver bean.Webservice por más información
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEST_JPA_3", new java.util.HashMap());
		EntityManager em2 = (EntityManager) emf.createEntityManager();
		
		ws.setName(name);
		ws.setUrl(url);
		try {
			em.persist(ws);
		} catch(Exception e) {
			e.printStackTrace();
			throw new RemoteException(e.getMessage());
		} 
		return ws;
	}

}
