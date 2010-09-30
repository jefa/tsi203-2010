package partuzabook.datos.persistencia.DAO;

import javax.ejb.Remote;

import partuzabook.datos.persistencia.beans.Participant;
import partuzabook.datos.persistencia.beans.ParticipantPK;

@Remote
public interface ParticipantDAO extends Dao<ParticipantPK, Participant>{
	
	//TODO: Preguntar si quieren que la creacion de la entidad sea en el DAO o que la haga la logica
	//Comment create(String username, String password);
	
}
