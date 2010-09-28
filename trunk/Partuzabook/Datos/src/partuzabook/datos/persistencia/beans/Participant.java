package partuzabook.datos.persistencia.beans;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the participants database table.
 * 
 */
@Entity
@Table(name="participants")
public class Participant implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ParticipantPK id;

	@Column(name="reg_date")
	private Timestamp regDate;

	//bi-directional many-to-one association to Event
    @ManyToOne
	@JoinColumn(name="evt_id")
	private Event event;

	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="usr_id")
	private User user;

    public Participant() {
    }

	public ParticipantPK getId() {
		return this.id;
	}

	public void setId(ParticipantPK id) {
		this.id = id;
	}
	
	public Timestamp getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}