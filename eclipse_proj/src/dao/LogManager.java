package dao;

import java.rmi.RemoteException;

import javax.ejb.EJBMetaData;
import javax.ejb.FinderException;
import javax.ejb.Handle;
import javax.ejb.HomeHandle;
import javax.ejb.RemoveException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bean.Log;
import bean.LogPK;
import client.ILogManager;

public class LogManager implements ILogManager {

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

	public Log create(int ID, String TEXT) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public Log findByPrimaryKey(LogPK key) throws RemoteException,
			FinderException {
		// TODO Auto-generated method stub
		return null;
	}

}
