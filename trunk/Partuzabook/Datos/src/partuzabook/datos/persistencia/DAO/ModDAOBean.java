package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import partuzabook.datos.persistencia.beans.Mod;
import partuzabook.datos.persistencia.beans.ModPK;

@Stateless
public class ModDAOBean extends JpaDao<ModPK, Mod> implements ModDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@Override
	public List<Mod> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
