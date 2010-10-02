package partuzabook.datos.persistencia.beans;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("N")
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
