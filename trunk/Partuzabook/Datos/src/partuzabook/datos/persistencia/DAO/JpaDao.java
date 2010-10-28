package partuzabook.datos.persistencia.DAO;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class JpaDao<K, E> implements Dao<K, E> {
	protected Class<E> entityClass;
	
	@PersistenceContext
	private EntityManager em;
	
	public void flush() {
		em.flush();
	}
	
	public JpaDao() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];		
	}
	
	public void persist(E entity) {
		em.persist(entity);
	}
	
	public void remove(E entity) {
		em.remove(entity);
	}
	
	public E findByID(K id) {
		return em.find(entityClass, id);
	}

}
