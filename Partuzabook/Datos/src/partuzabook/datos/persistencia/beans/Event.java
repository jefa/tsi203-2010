package partuzabook.datos.persistencia.beans;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the events database table.
 * 
 */
@Entity
@Table(name="events")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "flags", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("E")
@NamedQueries({
	@NamedQuery(name = "Event.findAll", query = "SELECT o FROM Event o"),
	@NamedQuery(name = "Event.findByName", query = "SELECT o FROM Event o WHERE o.evtName = :name"),
	@NamedQuery(name = "Event.findAllAfterDate",
			query = "SELECT o FROM Event o WHERE o.date >= :after"),
	@NamedQuery(name = "Event.findContentById",
			query = "SELECT c FROM Content c WHERE c.id = :content")
})
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="evt_id_auto")
	private Integer evtIdAuto;

	private String address;

    @Temporal( TemporalType.DATE)
	private Date date;

	private String description;

	private Integer duration;

	@Column(name="evt_name")
	private String evtName;

	@Column(name="reg_date")
	private Timestamp regDate;

	//bi-directional one-to-one association to Album
	@OneToOne(mappedBy="event")
	private Album album;

	//bi-directional many-to-one association to Content
	@OneToMany(mappedBy="event")
	private List<Content> contents;

	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="creator")
	private Admin creator;
	
	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="myEvents")
	@JoinTable(name="Participants", 
          joinColumns=@JoinColumn(name="evt_id"),
          inverseJoinColumns=@JoinColumn(name="usr_id"))
	private List<NormalUser> myParticipants;

    public Event() {
    }

	public Integer getEvtIdAuto() {
		return this.evtIdAuto;
	}

	public void setEvtIdAuto(Integer evtIdAuto) {
		this.evtIdAuto = evtIdAuto;
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

	public String getEvtName() {
		return this.evtName;
	}

	public void setEvtName(String evtName) {
		this.evtName = evtName;
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
	
	public List<Content> getContents() {
		return this.contents;
	}

	public void setContents(List<Content> contents) {
		this.contents = contents;
	}
	
	public Admin getCreator() {
		return this.creator;
	}

	public void setCreator(Admin creator) {
		this.creator = creator;
	}
		
	public List<NormalUser> getMyParticipants() {
		return this.myParticipants;
	}

	public void Participants(List<NormalUser> myParticipants) {
		this.myParticipants = myParticipants;
	}
	
}