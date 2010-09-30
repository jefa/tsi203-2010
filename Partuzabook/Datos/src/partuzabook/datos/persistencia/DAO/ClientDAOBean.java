package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.Admin;
import partuzabook.datos.persistencia.beans.Client;

@Stateless
public class ClientDAOBean extends JpaDao<String, Client> implements ClientDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@Override
	public List<Client> findAll() {
		Query namedQuery = em.createNamedQuery("Client.findAll");
		return (List<Client>)namedQuery.getResultList();
	}


}
