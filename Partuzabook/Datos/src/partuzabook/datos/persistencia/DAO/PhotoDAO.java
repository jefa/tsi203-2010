package partuzabook.datos.persistencia.DAO;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.Content;
import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.Photo;

@Local
public interface PhotoDAO extends Dao<Integer, Photo>{
	
	public Photo findBestRatedInEvent(Event event);
	
	public Photo findMostCommentedInEvent(Event event);
	
	//TODO: Preguntar si quieren que la creacion de la entidad sea en el DAO o que la haga la logica
	//Comment create(String username, String password);
	
}
