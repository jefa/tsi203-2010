package partuzabook.datos.persistencia.beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("P")
@NamedQueries({
	@NamedQuery(name = "Photo.findAll", query = "SELECT o FROM Photo o"),
	@NamedQuery(name = "Photo.findBestRatedInEvent", 
			query = "SELECT p FROM Rating r, Photo p WHERE " +
					"p.event = :event AND r.content = p GROUP BY " +
					"p.cntIdAuto, p.album, p.event, p.posGallery, p.regDate, p.url, p.user, p.description, p.size" +
					" ORDER BY AVG(r.score) DESC"),
	@NamedQuery(name = "Photo.findMostCommentedInEvent", 
			query = "SELECT p FROM Comment cmt, Photo p WHERE " +
					"p.event = :event AND cmt.content = p GROUP BY " +
					"p.cntIdAuto, p.album, p.event, p.posGallery, p.regDate, p.url, p.user, p.description, p.size" +
					" ORDER BY COUNT(*) DESC")
	})
public class Photo extends SelfContent {
	private static final long serialVersionUID = 1L;
}
