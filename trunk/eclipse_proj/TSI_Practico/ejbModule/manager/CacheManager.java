package manager;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.Set;

import javax.ejb.FinderException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import bean.Cache;
import bean.CachePK;
import bean.Webservice;

public @Stateless class CacheManager implements ICacheManager {

	@PersistenceContext
	EntityManager em;
	
	public CacheManager() {
		
	}
	
	public CacheManager(EntityManager entityManager) {
		em = entityManager;
	}
	
	
	public Cache create(String params, String result, int idws, Date reg_date) throws Exception {
		Cache c = new Cache();
		
		Webservice ws = em.find(Webservice.class, idws);
		
		if(ws == null)
			throw new RemoteException("No existe el Webservice con idws" + idws);
		
		//El ID se asigna automaticamente. Ver bean.Cache por mas informacion
		CachePK cpk = new CachePK();
		cpk.setIdws(idws);
		cpk.setParams(params);
		c.setId(cpk);
		c.setResult(result);
		c.setReg_date(reg_date);
		c.setWebservice(ws);

		//Agregamos a c a la lista de Caches de ws
		Set<Cache> sC = ws.getCaches();
		sC.add(c);
		ws.setCaches(sC);
		
		em.persist(ws);
		em.persist(c);
		
		
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
	
	public void update(Cache c, String result, Date reg_date) {
		c.setResult(result);
		c.setReg_date(reg_date);
		//em.persist(c);/
		em.merge(c);
	}

}
