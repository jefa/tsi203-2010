package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.Admin;
import partuzabook.datos.persistencia.beans.Participant;
import partuzabook.datos.persistencia.beans.ParticipantPK;

@Stateless
public class ParticipantDAOBean extends JpaDao<ParticipantPK, Participant> implements ParticipantDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@Override
	public List<Participant> findAll() {
		Query namedQuery = em.createNamedQuery("Participant.findAll");
		return (List<Participant>)namedQuery.getResultList();
	}


}
