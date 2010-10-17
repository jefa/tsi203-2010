package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.EvtCategory;

@Local
public interface EvtCategoryDAO extends Dao<String, EvtCategory>{

	public List<EvtCategory> findAll();

}
