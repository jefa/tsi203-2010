package partuzabook.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

// Code taken from http://jcsnippets.atspace.com/java/gui-graphics/create-thumbnail.html

public class CreateThumbnail extends JFrame { 

	public static final int VERTICAL = 0;
	public static final int HORIZONTAL = 1;
	public static final int CLIP_AND_SCALE = 2;

	public static final String IMAGE_JPEG = "jpeg";
	public static final String IMAGE_JPG = "jpg";
	public static final String IMAGE_PNG = "png";

	private ImageIcon image;
	private ImageIcon thumb;

	public CreateThumbnail(Image image)	{
		this.image = new ImageIcon(image); 
	}

	public CreateThumbnail(String fileName)	{
		image = new ImageIcon(fileName);
	}

	public Image getThumbnail(int size, int dir) {
		if (dir == HORIZONTAL)
		{
			thumb = new ImageIcon(
					image.getImage().getScaledInstance(
							size, -1, Image.SCALE_SMOOTH));
		}
		else if	(dir == VERTICAL) {
			thumb = new ImageIcon(
					image.getImage().getScaledInstance(
							-1, size, Image.SCALE_SMOOTH));
		}
		else if	(dir == CLIP_AND_SCALE) {
			int w = image.getIconWidth();
			int h = image.getIconHeight();
			int x = 0, y = 0;
			if (w > h) {
				x = (w - h) / 2;
				w = h;
			}
			else {
				y = (h - w) / 2;
				h = w;
			}
			Image tempImage = createImage(new FilteredImageSource(
				image.getImage().getSource(), new CropImageFilter(x, y, w, h)));
			thumb = new ImageIcon(
					tempImage.getScaledInstance(size, -1, Image.SCALE_SMOOTH));
		}
		return thumb.getImage();
	}

	public Image getThumbnail(int size, int dir, int scale) {
		if (dir == HORIZONTAL) {
			thumb = new ImageIcon(
					image.getImage().getScaledInstance(size, -1, scale));
		}
		else {
			thumb = new ImageIcon(
					image.getImage().getScaledInstance(-1, size, scale));
		}
		return thumb.getImage();
	}

	public void saveThumbnail(File file, String imageType) {
		if (thumb != null) {
			BufferedImage bi = new BufferedImage(
					thumb.getIconWidth(), 
					thumb.getIconHeight(), 
					BufferedImage.TYPE_INT_RGB);
			Graphics g = bi.getGraphics();
			g.drawImage(thumb.getImage(), 0, 0, null);
			try {
				ImageIO.write(bi, imageType, file);
			}
			catch (IOException ioe) {
				System.out.println("Error occured saving thumbnail");
			}
		}
		else {
			System.out.println("Thumbnail has not yet been created");
		}
	}

	public static void main(String [] args) {
		CreateThumbnail ct = new CreateThumbnail("image.jpg");
		ct.getThumbnail(100, CreateThumbnail.HORIZONTAL);
		ct.saveThumbnail(new File("thumb.jpg"), CreateThumbnail.IMAGE_JPEG);
	}

}
