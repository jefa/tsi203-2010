package manager;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.Set;

import javax.ejb.EJBMetaData;
import javax.ejb.FinderException;
import javax.ejb.Handle;
import javax.ejb.HomeHandle;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bean.Log;
import bean.LogPK;
import bean.Webservice;

public @Stateless class LogManager implements ILogManager {

	@PersistenceContext
	EntityManager em;
	
	public LogManager() {
		
	}
	
	public LogManager(EntityManager entityManager) {
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

	public Log create(int idws, Date date, String outcome) throws Exception{
		
		Webservice ws = em.find(Webservice.class, idws);
		
		if(ws == null)
			throw new Exception("No existe el Webservice con id " + idws);
		
		Log l = new Log();
		LogPK lpk = new LogPK();
		lpk.setIdws(idws);
		lpk.setDate(date);
		
		l.setId(lpk);
		l.setOutcome(outcome);
		l.setWebservice(ws);
		
		em.persist(l);
		
		//Agregamos a l a la lista de Log de ws
		Set<Log> sL = ws.getLogs();
		sL.add(l);
		ws.setLogs(sL);
		
		em.persist(ws);
		
		return l;
	}

	public Log findByPrimaryKey(LogPK key) throws FinderException {
				
		return null;
	}

}
