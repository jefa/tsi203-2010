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
public abstract class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="evt_name")
	private String evtName;

	private String address;

	@Column(name="album_url")
	private String albumUrl;

    @Temporal( TemporalType.DATE)
	private Date date;

	private String description;

	private Integer duration;

	private String flags;

	@Column(name="reg_date")
	private Timestamp regDate;

	//bi-directional many-to-one association to Album
	@OneToMany(mappedBy="event")
	private Set<Album> albums;

	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="creator")
	private Admin admin;

	//bi-directional many-to-one association to Participant
	@OneToMany(mappedBy="event")
	private Set<Participant> participants;

    public Event() {
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

	public String getAlbumUrl() {
		return this.albumUrl;
	}

	public void setAlbumUrl(String albumUrl) {
		this.albumUrl = albumUrl;
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

	public String getFlags() {
		return this.flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}

	public Timestamp getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public Set<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
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