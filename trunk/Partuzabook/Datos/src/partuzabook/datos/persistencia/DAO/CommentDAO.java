package partuzabook.datos.persistencia.DAO;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.Comment;
import partuzabook.datos.persistencia.beans.CommentPK;

@Local
public interface CommentDAO extends Dao<CommentPK, Comment>{
	
	//TODO: Preguntar si quieren que la creacion de la entidad sea en el DAO o que la haga la logica
	//Comment create(String username, String password);
	
}
