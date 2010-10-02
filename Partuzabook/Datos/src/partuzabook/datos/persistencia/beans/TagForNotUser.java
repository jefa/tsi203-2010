package partuzabook.datos.persistencia.beans;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("N")
@NamedQueries({
	@NamedQuery(name = "TagForNotUser.findAll", query = "SELECT o FROM TagForNotUser o")
	})
public class TagForNotUser extends Tag{
	private static final long serialVersionUID = 1L;
	
	@Column(name="usr_tag_custom")
	private String usrTagCustom;
	
	public String getUsrTagCustom() {
		return this.usrTagCustom;
	}

	public void setUsrTagCustom(String usrTagCustom) {
		this.usrTagCustom = usrTagCustom;
	}


}
