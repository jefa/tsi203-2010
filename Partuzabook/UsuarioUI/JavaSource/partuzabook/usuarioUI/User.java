package partuzabook.usuarioUI;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

public class User implements Serializable {

	private static final long serialVersionUID = -6377385701609824121L;
	
	private Long id;
	private String passwordHash;
	private String firstName;
	private String secondName;
	private String email;
	private String login;
	private String password;
	private String confirmPassword;
	private Date birthDate;
	private Sex sex = Sex.MALE;
	private Boolean hasAvatar;
	private boolean preDefined;
	private ArrayList<Album> albums = new ArrayList<Album>();

	public User() {
		super();
		this.id = new Long(10);
		this.firstName = "Javier";
		this.secondName = "Ernesto";
		this.email = "jefa55@yahoo.com";
		this.login = "jefa55";
		this.password = "pepe";
		this.confirmPassword = "pepe";
		this.birthDate = new Date();
		this.sex = Sex.MALE;
		this.hasAvatar = false;
		this.preDefined = false;
		this.albums = new ArrayList<Album>();
		loadAlbums();
	}
	
	@PostConstruct
	private void loadAlbums(){
		int imgCount = 0;
		for (int i=0; i<6; i++){
			Album a = new Album();
			a.setId(new Long(i));
			a.setCreated(new Date());
			a.setDescription("albyum "+i+" descri");
			a.setName("album"+i);
			a.setShared(true);
			for (int j=0; j<3; j++){
				Image img = new Image();
				img.setId(new Long(i+j));
				img.setAlbum(a);
				img.setDescription("img"+(imgCount)+" description");
				img.setName("("+imgCount+").jpg");
				img.setThumbName("("+imgCount+")_tn.jpg");
				//img.setPath("imgPath");
				a.addImage(img);
				imgCount++;
			}
			this.albums.add(a);
		}
		
		//Add an empty album
		Album a = new Album();
		a.setId(new Long(6));
		a.setCreated(new Date());
		a.setDescription("albyum "+6+" descri");
		a.setName("album"+6);
		a.setShared(true);
		this.albums.add(a);

	}

	public boolean isPreDefined() {
		return preDefined;
	}

	public void setPreDefined(boolean preDefined) {
		this.preDefined = preDefined;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Long getId() {
		return id;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Boolean getHasAvatar() {
		return hasAvatar;
	}

	public void setHasAvatar(Boolean hasAvatar) {
		this.hasAvatar = hasAvatar;
	}

	public String getPath() {
		if (this.getId() == null) {
			return null;
		}
		return File.separator + this.getLogin() + File.separator;
	}

	public List<Image> getImages() {
		final List<Image> images = new ArrayList<Image>();
		for (Album a : getAlbums()) {
			images.addAll(a.getImages());
		}
		return images;
	}

	public List<Album> getAlbums() {
//		System.out.println("User.getAlbums()::::::::::: albums="+albums);
//		try{
//			throw new Exception("======================EN USER ALBUMM");
//		} catch (Exception e){
//			e.printStackTrace();
//		}
		return albums;
		//return new ArrayList<Album>();
	}

	public List<Image> getSharedImages() {
		final List<Image> images = new ArrayList<Image>();
		for (Album a : getAlbums()) {
			if (!a.isShared()) {
				continue;
			}
			images.addAll(a.getImages());
		}
		return images;
	}

	public List<Album> getSharedAlbums() {
		final List<Album> albums = new ArrayList<Album>();
		for (Album a : getAlbums()) {
			if (!a.isShared()) {
				continue;
			}
			albums.add(a);
		}
		return albums;
	}

	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final User user = (User) o;

		if (id != null ? !id.equals(user.id) : user.id != null) return false;
		if (login != null ? !login.equals(user.login) : user.login != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (login != null ? login.hashCode() : 0);
		return result;
	}
	
}