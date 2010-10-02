package partuzabook.datos.persistencia.beans;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the events database table.
 * 
 */
@Entity
@Table(name="events")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "flags", discriminatorType = DiscriminatorType.STRING)
@NamedQueries({
	@NamedQuery(name = "Event.findAll", query = "SELECT o FROM Event o"),
	@NamedQuery(name = "Event.findByName", query = "SELECT o FROM Event o WHERE o.evtName = :name"),
	@NamedQuery(name = "Event.findAllAfterDate",
			query = "SELECT o FROM Event o WHERE o.date >= :after"),
	@NamedQuery(name = "Event.findContentById",
			query = "SELECT c FROM Content c WHERE c.date >= :after")
})
public abstract class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="evt_id_auto")
	private Integer evtIdAuto;
	
	@Column(name="evt_name")
	private String evtName;

	private String address;

	@Temporal( TemporalType.DATE)
	private Date date;

	private String description;

	private Integer duration;

	@Column(name="reg_date")
	private Timestamp regDate;

	//bi-directional one-to-one association to Album
	@OneToOne(mappedBy="event")
	private Album album;

	//bi-directional many-to-one association to Content
	@OneToMany(mappedBy="event")
	private Set<Content> contents;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="creator")
	private Admin admin;

	//bi-directional many-to-one association to Participant
	@OneToMany(mappedBy="event")
	private Set<Participant> participants;

	public Event() {
	}
	
	public Integer getEvtIdAuto() {
		return this.evtIdAuto;
	}

	public void setEvtIdAuto(Integer evtIdAuto) {
		this.evtIdAuto = evtIdAuto;
	}

	public String getEvtName() {
		return this.evtName;
	}

	public void setEvtName(String evtName) {
		this.evtName = evtName;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDuration() {
		return this.duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Timestamp getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Set<Content> getContents() {
		return this.contents;
	}

	public void setContents(Set<Content> contents) {
		this.contents = contents;
	}
	
	public Admin getCreator() {
		return this.admin;
	}

	public void setCreator(Admin admin) {
		this.admin = admin;
	}

	public Set<Participant> getParticipants() {
		return this.participants;
	}

	public void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}

}