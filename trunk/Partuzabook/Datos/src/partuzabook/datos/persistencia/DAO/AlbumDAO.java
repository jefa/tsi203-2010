package partuzabook.datos.persistencia.DAO;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.Album;

@Local
public interface AlbumDAO extends Dao<Integer, Album>{
		
}
