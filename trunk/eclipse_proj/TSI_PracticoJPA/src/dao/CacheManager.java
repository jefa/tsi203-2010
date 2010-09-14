package dao;

import java.rmi.RemoteException;

import javax.ejb.EJBMetaData;
import javax.ejb.Handle;
import javax.ejb.HomeHandle;
import javax.ejb.RemoveException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bean.Cache;
import client.ICacheManager;

public class CacheManager implements ICacheManager {

	@PersistenceContext
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
		// TODO Auto-generated method stub

	}

	public Cache create(int ID, String params, String result) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
