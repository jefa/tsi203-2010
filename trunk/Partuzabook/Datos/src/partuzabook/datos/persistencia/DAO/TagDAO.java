package partuzabook.datos.persistencia.DAO;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.Tag;
import partuzabook.datos.persistencia.beans.TagPK;

@Local
public interface TagDAO extends Dao<TagPK, Tag>{
	
	
}
