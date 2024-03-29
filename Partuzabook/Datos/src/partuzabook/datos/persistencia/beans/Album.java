package partuzabook.datos.persistencia.beans;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the album database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Album.findAllAfterDate",
			query = "SELECT o FROM Album o WHERE o.regDate >= :after"),
	@NamedQuery(name = "Album.findAll", query = "SELECT o FROM Album o")
	})
public class Album implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="alb_id_auto")
	private Integer albIdAuto;

	@Column(name="album_url")
	private String albumUrl;

	@Column(name="reg_date")
	private Timestamp regDate;

	//bi-directional one-to-one association to Event
	@OneToOne
	@JoinColumn(name="evt_id")
	private Event event;

    public Album() {
    }

	public Integer getAlbIdAuto() {
		return this.albIdAuto;
	}

	public void setAlbIdAuto(Integer albIdAuto) {
		this.albIdAuto = albIdAuto;
	}

	public String getAlbumUrl() {
		return this.albumUrl;
	}

	public void setAlbumUrl(String albumUrl) {
		this.albumUrl = albumUrl;
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
	
}