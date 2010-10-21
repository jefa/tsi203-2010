package partuzabook.datatypes;

import java.io.Serializable;

public class DatatypeMostTagged implements Serializable {
	private static final long serialVersionUID = 1L;
		
	public DatatypeUser user;
	
	public Integer cantTags;
	
	public DatatypeMostTagged() {
		
	}

	public DatatypeUser getUser() {
		return user;
	}

	public void setUser(DatatypeUser user) {
		this.user = user;
	}

	public Integer getCantTags() {
		return cantTags;
	}

	public void setCantTags(Integer cantTags) {
		this.cantTags = cantTags;
	}
}
