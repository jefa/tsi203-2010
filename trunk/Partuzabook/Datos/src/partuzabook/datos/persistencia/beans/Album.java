package partuzabook.datos.persistencia.beans;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the album database table.
 * 
 */
@Entity
public class Album implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AlbumPK id;

	@Column(name="reg_date")
	private Timestamp regDate;

	//bi-directional many-to-one association to Content
    @ManyToOne
	@JoinColumn(name="cnt_id", insertable=false, updatable=false)
	private Content content;

	//bi-directional many-to-one association to Event
    @ManyToOne
	@JoinColumn(name="evt_id", insertable=false, updatable=false)
	private Event event;

    public Album() {
    }

	public AlbumPK getId() {
		return this.id;
	}

	public void setId(AlbumPK id) {
		this.id = id;
	}
	
	public Timestamp getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public Content getContent() {
		return this.content;
	}

	public void setContent(Content content) {
		this.content = content;
	}
	
	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
}