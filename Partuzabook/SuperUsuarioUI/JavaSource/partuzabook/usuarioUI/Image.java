package partuzabook.usuarioUI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Image {

	private Long id;
	private List<Comment> comments = new ArrayList<Comment>();
	private Album album;
	private String name;
	private String thumbName;
	private boolean covering;
	private String path;
	private String cameraModel;
	private int height;
	private double size;
	private int width;
	private Date uploaded;
	private String description;
	private Date created;
	private boolean allowComments;
	//private Boolean showMetaInfo = true;
	private boolean visited;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setThumbName(String string) {
		this.thumbName = string;
	}
	
	public String getThumbName() {
		return this.thumbName;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		//return this.uploadRoot + this.getId().toString() + File.separator;
		return this.album.getPath() + this.name;
	}

	public String getThumbPath() {
		//return this.uploadRoot + this.getId().toString() + File.separator;
		return this.album.getPath() + this.thumbName;
	}

	/*public void setPath(String path) {
		this.path = path;
	}*/

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public String getCameraModel() {
		return cameraModel;
	}

	public void setCameraModel(String cameraModel) {
		this.cameraModel = cameraModel;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Date getUploaded() {
		return uploaded;
	}

	public void setUploaded(Date uploaded) {
		this.uploaded = uploaded;
	}

	public boolean isAllowComments() {
		return allowComments;
	}

	public void setAllowComments(boolean allowComments) {
		this.allowComments = allowComments;
	}

	public boolean isCovering() {
		return covering;
	}

	public void setCovering(boolean covering) {
		this.covering = covering;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean isNew() {
		/*if (!visited) {
			return this.getUploaded().after(ActionTools.getRecentlyDate());
		}*/
		return false;
	}

	public void addComment(Comment comment) {
		if (comment == null) {
			throw new IllegalArgumentException("Null comment!");
		}
		comment.setImage(this);
		comments.add(comment);
	}

	public void removeComment(Comment comment) {
		if (comment == null) {
			throw new IllegalArgumentException("Null comment");
		}
		if (comment.getImage().equals(this)) {
			comment.setImage(null);
			comments.remove(comment);
		} else {
			throw new IllegalArgumentException("Comment not belongs to this image");
		}
	}

	public String getFullPath() {
		if (getAlbum().getPath() == null) {
			return null;
		}

		return getAlbum().getPath() + this.path;
	}

	public User getOwner() {
		return getAlbum().getOwner();
	}

	public boolean isOwner(User user) {
		return user != null && user.equals(getOwner());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		final Image image = (Image) obj;

		return (id == null ? image.getId() == null : id.equals(image.getId()))
				&& (path == null ? image.getPath() == null : path.equals(image.getPath()));
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (path != null ? path.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "{id : "+getId()+", name : "+getName()+"}";
	}

}
