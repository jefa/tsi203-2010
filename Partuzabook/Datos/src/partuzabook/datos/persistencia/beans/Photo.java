package partuzabook.datos.persistencia.beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("P")
@NamedQueries({
	@NamedQuery(name = "Photo.findAll", query = "SELECT o FROM Photo o"),
	@NamedQuery(name = "Photo.findBestRatedInEvent", query = "SELECT o FROM Photo o"),
	@NamedQuery(name = "Photo.findMostCommentedInEvent", 
			query = "SELECT c FROM Comment cmt, Event e, Content c WHERE " +
					"c.Event = e AND cmt.Content = c GROUP BY cmt.cnt_id ORDER BY COUNT(*)")			
	})
public class Photo extends SelfContent {
	private static final long serialVersionUID = 1L;
}
