package partuzabook.datos.persistencia.DAO;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import partuzabook.datos.persistencia.beans.Admin;

@Stateless
public class AdminDAOBean extends JpaDao<String, Admin> implements AdminDAO {

	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	/*
	@Override
	public Admin create(String username, String password) {
		//UserTransaction ut = sc.getUserTransaction();		
		try {
			//ut.begin();
			//em.getTransaction().begin();
			Admin admin = new Admin();
			admin.setUsername(username);
			admin.setPassword(password);
			admin.setRegDate(new Timestamp((new java.util.Date()).getTime()));
			em.persist(admin);
			//ut.commit();
			//em.getTransaction().commit();
			return admin;
		} catch(Exception e) {
			try {
				//ut.rollback();
				//em.getTransaction().rollback();				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		}
	}

*/
	@Override
	public List<Admin> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
