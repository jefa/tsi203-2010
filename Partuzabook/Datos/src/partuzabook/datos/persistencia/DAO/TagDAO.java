package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.Tag;

@Local
public interface TagDAO extends Dao<Integer, Tag>{
	
}
