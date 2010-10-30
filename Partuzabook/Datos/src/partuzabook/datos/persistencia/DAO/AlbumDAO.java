package partuzabook.datos.persistencia.DAO;

import java.sql.Date;
import java.util.List;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.Album;
import partuzabook.datos.persistencia.beans.Event;

@Local
public interface AlbumDAO extends Dao<Integer, Album>{
		
	public List<Album> findAllAfterDate(Date date);

}
