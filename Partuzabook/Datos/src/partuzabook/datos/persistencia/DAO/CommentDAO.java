package partuzabook.datos.persistencia.DAO;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.Comment;
import partuzabook.datos.persistencia.beans.CommentPK;

@Local
public interface CommentDAO extends Dao<CommentPK, Comment>{
	
	
}
