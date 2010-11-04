package partuzabook.datos.persistencia.beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("Q")
@NamedQueries({
	@NamedQuery(name = "ExternalPhoto.findAll", query = "SELECT o FROM ExternalPhoto o")
	})
public class ExternalPhoto extends Content {
	private static final long serialVersionUID = 1L;

}
