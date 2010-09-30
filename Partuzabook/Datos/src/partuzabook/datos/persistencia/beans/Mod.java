package partuzabook.datos.persistencia.beans;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the mods database table.
 * 
 */
@Entity
@Table(name="mods")
@NamedQueries({
	@NamedQuery(name = "Mod.findAll", query = "SELECT o FROM Mod o")
	})
public class Mod implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ModPK id;

	@Column(name="reg_date")
	private Timestamp regDate;

	//bi-directional many-to-one association to Event
    @ManyToOne
	@JoinColumn(name="evt_id", insertable=false, updatable=false)
	private ModeratedEvent event;

	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="usr_id", insertable=false, updatable=false)
	private Client user;

    public Mod() {
    }

	public ModPK getId() {
		return this.id;
	}

	public void setId(ModPK id) {
		this.id = id;
	}
	
	public Timestamp getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public ModeratedEvent getModeratedEvent() {
		return this.event;
	}

	public void setModeratedEvent(ModeratedEvent event) {
		this.event = event;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(Client user) {
		this.user = user;
	}
	
}