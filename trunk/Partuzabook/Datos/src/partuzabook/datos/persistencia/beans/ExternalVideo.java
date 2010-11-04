package partuzabook.datos.persistencia.beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("W")
@NamedQueries({
	@NamedQuery(name = "ExternalVideo.findAll", query = "SELECT o FROM ExternalVideo o")
	})
public class ExternalVideo extends Content {
	private static final long serialVersionUID = 1L;

}
