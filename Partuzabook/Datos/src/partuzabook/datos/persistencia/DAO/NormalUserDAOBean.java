package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.Content;
import partuzabook.datos.persistencia.beans.NormalUser;
import partuzabook.datos.persistencia.beans.Notification;

@Stateless
public class NormalUserDAOBean extends JpaDao<String, NormalUser> implements NormalUserDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@Override
	public List<NormalUser> findAll() {
		Query namedQuery = em.createNamedQuery("NormalUser.findAll");
		return (List<NormalUser>)namedQuery.getResultList();
	}
	
	@Override
	public NormalUser findByFacebookId(long facebookid) {
		Query namedQuery = em.createNamedQuery("NormalUser.findByFacebookId");
		namedQuery.setParameter("facebookid", facebookid);
		if (namedQuery.getResultList().size() > 0) {
			return (NormalUser) namedQuery.getSingleResult();	
		} else {
			return null;
		}	
		
		
	}

}
