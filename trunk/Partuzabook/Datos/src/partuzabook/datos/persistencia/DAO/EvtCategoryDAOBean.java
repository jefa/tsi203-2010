package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.EvtCategory;

@Stateless
public class EvtCategoryDAOBean extends JpaDao<String, EvtCategory> implements EvtCategoryDAO {
	
	@PersistenceContext
	EntityManager em;
		
	@SuppressWarnings("unchecked")
	@Override
	public List<EvtCategory> findAll() {
		Query namedQuery = em.createNamedQuery("EvtCategory.findAll");
		return (List<EvtCategory>)namedQuery.getResultList();
	}

}
