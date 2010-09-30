package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.Mod;
import partuzabook.datos.persistencia.beans.ModPK;
import partuzabook.datos.persistencia.beans.ModeratedEvent;

@Stateless
public class ModDAOBean extends JpaDao<ModPK, Mod> implements ModDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@Override
	public List<Mod> findAll() {
		Query namedQuery = em.createNamedQuery("Mod.findAll");
		return (List<Mod>)namedQuery.getResultList();
	}


}
