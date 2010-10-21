package partuzabook.datos.persistencia.DAO;

import java.util.List;
import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.TagForUser;

@Local
public interface TagForUserDAO extends Dao<Integer, TagForUser>{
	public List<String> getMostTagged();
}
