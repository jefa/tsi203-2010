package partuzabook.datos.persistencia.DAO;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.Content;
import partuzabook.datos.persistencia.beans.ContentPK;
import partuzabook.datos.persistencia.beans.Event;

@Local
public interface ContentDAO extends Dao<ContentPK, Content>{
	Content findByIDInEvent(Event event, int contentID);
	//TODO: Preguntar si quieren que la creacion de la entidad sea en el DAO o que la haga la logica
	//Comment create(String username, String password);
	
}
