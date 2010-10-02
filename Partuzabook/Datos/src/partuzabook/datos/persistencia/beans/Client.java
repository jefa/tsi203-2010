package partuzabook.datos.persistencia.beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@DiscriminatorValue("C")
@NamedQueries({
	@NamedQuery(name = "Client.findAll", query = "SELECT o FROM Client o")
	})
public class Client extends NormalUser{
	private static final long serialVersionUID = 1L;
	
}
