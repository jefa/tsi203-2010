package partuzabook.datos.persistencia.beans;


import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the tags database table.
 * 
 */
@Entity
@Table(name="tags")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "flags", discriminatorType = DiscriminatorType.STRING)
@NamedQueries({
	@NamedQuery(name = "Tag.findAll", query = "SELECT o FROM Tag o")
	})
public abstract class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TagPK id;

	private String flags;

	@Column(name="\"posX\"")
	private Integer _posX_;

	@Column(name="\"posY\"")
	private Integer _posY_;

	@Column(name="reg_date")
	private Timestamp regDate;

	@Column(name="usr_tag_custom")
	private String usrTagCustom;

	//bi-directional many-to-one association to Content
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="cnt_id", referencedColumnName="cnt_id_auto"),
		@JoinColumn(name="evt_id", referencedColumnName="evt_id")
		})
	private Content content;

	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="creator")
	private User creator;

	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="usr_tag")
	private User userTagged;

    public Tag() {
    }

	public TagPK getId() {
		return this.id;
	}

	public void setId(TagPK id) {
		this.id = id;
	}
	
	public String getFlags() {
		return this.flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}

	public Integer get_posX_() {
		return this._posX_;
	}

	public void set_posX_(Integer _posX_) {
		this._posX_ = _posX_;
	}

	public Integer get_posY_() {
		return this._posY_;
	}

	public void set_posY_(Integer _posY_) {
		this._posY_ = _posY_;
	}

	public Timestamp getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public String getUsrTagCustom() {
		return this.usrTagCustom;
	}

	public void setUsrTagCustom(String usrTagCustom) {
		this.usrTagCustom = usrTagCustom;
	}

	public Content getContent() {
		return this.content;
	}

	public void setContent(Content content) {
		this.content = content;
	}
	
	public User getCreator() {
		return this.creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	public User getUserTagged() {
		return this.userTagged;
	}

	public void setUserTagged(User userTagged) {
		this.userTagged = userTagged;
	}
	
}