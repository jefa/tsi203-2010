package partuzabook.usuarioUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Album implements Serializable {

	private static final long serialVersionUID = 5435887117917488034L;
	
	public Long id;
	public List<Image> images = new ArrayList<Image>();
	public Image coveringImage;
	public Date created;
	public String name;
	public String description;
	public boolean shared;
	public boolean empty = true;
	public String uploadRoot = "/uploadRoot/";

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Image> getImages() {
		return images;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setCoveringImage(Image coveringImage) {
		this.coveringImage = coveringImage;
	}

	public void addImage(Image image) { // TODO
		if (image == null) {
			throw new IllegalArgumentException("Null image!");
		}
		if (this.getImages().contains(image)) {
			//If album contain this image already
			return;
		}
		if (image.getAlbum() != null && !this.equals(image.getAlbum())) {
			//Remove from previous album
			image.getAlbum().removeImage(image);
		}
		image.setAlbum(this);
		images.add(image);
		this.empty = false;
	}

	public void removeImage(Image image) {
		if (image == null) {
			throw new IllegalArgumentException("Null image");
		}
		if (!image.getAlbum().equals(this)) {
			throw new IllegalArgumentException("This album not contain this image!");
		}

		if (getCoveringImage().equals(image)) {
			setCoveringImage(null);
		}

		images.remove(image);
		if (images.size() == 0){
			this.empty = true;
		}
	}

	public User getOwner() {
		return null;
	}

	public boolean isOwner(User user) {
		return user != null && user.equals(getOwner());
	}

	public int getIndex(Image image) {
		if (isEmpty()) {
			return -1;
		}

		return images.indexOf(image);
	}

	public Image getCoveringImage() {
		if (coveringImage == null && !isEmpty()) {
			coveringImage = images.get(0);
		}

		return coveringImage;
	}

	public boolean isEmpty() {
		//return images == null || images.isEmpty();
		return this.empty;
	}

	public boolean getIsEmpty() {
		return this.empty;
	}

	public String getPath() {
		/*if (getOwner().getPath() == null) {
			return null;
		}*/
		//return getOwner().getPath() + this.getId().toString() + File.separator;
		return this.uploadRoot + this.getId().toString() + "/"/*File.separator*/;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		final Album album = (Album) obj;

		if (id != null ? !id.equals(album.id) : album.id != null) return false;
		if (!name.equals(album.name)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + name.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "{id : "+getId()+", name : "+getName()+"}";
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}

	public boolean isShared() {
		return shared;
	}
	
	public void listener(Album album){
		System.out.println("Album.showAlbum(): showing me: "+album);
	}	


    /*public File getFileByPath(String path) {
        if (this.uploadRoot != null) {
            //File result = new File(this.uploadRoot, path);
            File result = new File(getPath(), path);
            /*try {
            	final String resultCanonicalPath = result.getCanonicalPath();
                if (!resultCanonicalPath.startsWith(this.uploadRootPath)) {
                    result = null;
                }
                return result;
            } catch (IOException e) {
                result = null;
            }/
            return result;
        }
        return null;
    }*/
}