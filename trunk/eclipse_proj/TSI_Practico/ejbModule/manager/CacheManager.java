package manager;

import java.rmi.RemoteException;
import java.util.Set;

import javax.ejb.EJBMetaData;
import javax.ejb.FinderException;
import javax.ejb.Handle;
import javax.ejb.HomeHandle;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import bean.Cache;
import bean.Webservice;

public @Stateless class CacheManager implements ICacheManager {

	@PersistenceContext
	EntityManager em;
	
	public CacheManager() {
		
	}
	
	public CacheManager(EntityManager entityManager) {
		em = entityManager;
	}
	
	
	public Cache create(String params, String result, int idws) throws Exception {
		Cache c = new Cache();
		
		Webservice ws = em.find(Webservice.class, idws);
		
		if(ws == null)
			throw new RemoteException("No existe el Webservice con idws" + idws);
		
		//El ID se asigna automaticamente. Ver bean.Cache por mas informacion
		
		c.setParams(params);
		c.setResult(result);
		c.setWebservice(ws);
		
		int id = em.createNamedQuery("Cache.findAll").getResultList().size();
		c.setId(id);
		
		//Agregamos a c a la lista de Caches de ws
		Set<Cache> sC = ws.getCaches();
		sC.add(c);
		ws.setCaches(sC);
		
		em.persist(c);
		em.persist(ws);
		
		return c;
	}

	@Override
	public Cache findByParamsAndIdws(String params, int idws)
			throws FinderException {
		Cache c = null;
		try {
			c = (Cache) em.createNamedQuery("Cache.findByIdwsParams")
			.setParameter("idws", idws)
			.setParameter("params", params)
			.getSingleResult();
		}
		catch (NoResultException e) {
			
		}
		return c;
	}
	
	public void remove(Cache c) {
		em.remove(c);
	}

}
