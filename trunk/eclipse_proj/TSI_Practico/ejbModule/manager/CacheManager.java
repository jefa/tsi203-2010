package manager;

import java.rmi.RemoteException;
import java.util.Set;

import javax.ejb.EJBMetaData;
import javax.ejb.Handle;
import javax.ejb.HomeHandle;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bean.Cache;
import bean.Webservice;

public @Stateless class CacheManager implements ICacheManager {

	//@PersistenceContext(unitName="TSI_PracticoJPA", type=PersistenceContextType.TRANSACTION)
	//@PersistenceContext
	EntityManager em;
	
	public CacheManager(EntityManager entityManager) {
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
		// TODO Auto-generated method stub

	}

	public Cache create(String params, String result, int idws) throws RemoteException {
		Cache c = new Cache();
		
		Webservice ws = em.find(Webservice.class, idws);
		
		if(ws == null)
			throw new RemoteException("No existe el Webservice con idws" + idws);
		
		//El ID se asigna automaticamente. Ver bean.Cache por mas informacion
		
		c.setParams(params);
		c.setResult(result);
		c.setWebservice(ws);
		
		em.persist(c);
		
		//Agregamos a c a la lista de Caches de ws
		Set<Cache> sC = ws.getCaches();
		sC.add(c);
		ws.setCaches(sC);
		
		em.persist(ws);
		
		return c;
	}

}
