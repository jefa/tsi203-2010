package dao;

import java.rmi.RemoteException;

import javax.annotation.Resource;
import javax.ejb.EJBMetaData;
import javax.ejb.Handle;
import javax.ejb.HomeHandle;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import bean.Webservice;
import client.IWebserviceManager;

public @Stateless class WebserviceManager implements IWebserviceManager {

	//@PersistenceContext
	@Resource
	EntityManager em;
	
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
