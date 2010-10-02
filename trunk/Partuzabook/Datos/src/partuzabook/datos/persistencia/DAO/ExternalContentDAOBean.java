package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.ContentPK;
import partuzabook.datos.persistencia.beans.ExternalContent;

@Stateless
public class ExternalContentDAOBean extends JpaDao<ContentPK, ExternalContent> implements ExternalContentDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@Override
	public List<ExternalContent> findAll() {
		Query namedQuery = em.createNamedQuery("ExternalContent.findAll");
		return (List<ExternalContent>)namedQuery.getResultList();
	}


}
