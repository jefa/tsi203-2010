package partuzabook.datos.persistencia.DAO;

import java.util.List;

public interface Dao<K, E> {

	void persist(E entity);
	void remove(E entity);
	E findByID(K id);
	List<E> findAll();
 
}
