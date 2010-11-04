package partuzabook.datos.persistencia.beans;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class SelfContent extends Content {
	private static final long serialVersionUID = 1L;
	
	private Integer size;

	public Integer getSize() {
		return this.size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}


}
