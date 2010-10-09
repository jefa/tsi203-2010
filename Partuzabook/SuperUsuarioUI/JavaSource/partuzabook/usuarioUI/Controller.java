package partuzabook.usuarioUI;

import java.io.Serializable;

public class Controller implements Serializable {

	private static final long serialVersionUID = 1133565321265342924L;
	
	private Album selectedAlbum;
	private Image selectedImage;
	
	public void setSelectedAlbum(Album album){
		System.out.println("Controller.showAlbum():: "+album);
		this.selectedAlbum = album;
		//model.resetModel(NavigationEnum.ALBUM_PREVIEW, album.getOwner(), album.getShelf(), album, null, album.getImages());	
	}

	public Album getSelectedAlbum() {
		return selectedAlbum;
	}
	
	public void setSelectedImage(Image image){
		System.out.println("Controller.setSelectedImage():: "+image);
		//model.resetModel(NavigationEnum.ALBUM_IMAGE_PREVIEW, image.getAlbum().getOwner(), image.getAlbum().getShelf(), image.getAlbum(), image, image.getAlbum().getImages());
		//ALBUM_PREVIEW("includes/album.xhtml"),
		image.setVisited(true);
		this.selectedImage = image;
	}

	public Image getSelectedImage() {
		return selectedImage;
	}

}