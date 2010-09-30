package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.Comment;
import partuzabook.datos.persistencia.beans.CommentPK;
import partuzabook.datos.persistencia.beans.ExternalContent;

@Stateless
public class CommentDAOBean extends JpaDao<CommentPK, Comment> implements CommentDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@Override
	public List<Comment> findAll() {
		Query namedQuery = em.createNamedQuery("Comment.findAll");
		return (List<Comment>)namedQuery.getResultList();
	}


}
