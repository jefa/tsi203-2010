package partuzabook.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	public static final int FIXED_SIZE_SCALE_DOWN_ONLY = 3;
	public static final int MAX_DIMENSIONS = 4;
	public static final int MIN_DIMENSIONS = 4;

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

	public BufferedImage getThumbnail(int width, int height, int dir) {
		int imgX = 0, imgY = 0;
		int canvasX = 0, canvasY = 0;
		int canvasW = width, canvasH = height;
		if (dir == HORIZONTAL)
		{
			thumb = new ImageIcon(
					image.getImage().getScaledInstance(
							width, -1, Image.SCALE_SMOOTH));
			canvasH = thumb.getImage().getHeight(null);
		}
		else if	(dir == VERTICAL) {
			thumb = new ImageIcon(
					image.getImage().getScaledInstance(
							-1, height, Image.SCALE_SMOOTH));
			canvasW = thumb.getImage().getWidth(null);
		}
		else if	(dir == CLIP_AND_SCALE) {
			int w = image.getIconWidth();
			int h = image.getIconHeight();
			if (w > h) {
				imgX = (w - h) / 2;
				w = h;
			}
			else {
				imgY = (h - w) / 2;
				h = w;
			}
			Image tempImage = createImage(new FilteredImageSource(
					image.getImage().getSource(), new CropImageFilter(imgX, imgY, w, h)));
			thumb = new ImageIcon(
					tempImage.getScaledInstance(width, -1, Image.SCALE_SMOOTH));
			canvasH = canvasW;
		}
		else if	(dir == FIXED_SIZE_SCALE_DOWN_ONLY) {
			int w = image.getIconWidth();
			int h = image.getIconHeight();

			if (width > w && height > h) {
				// No se escala
				canvasX = (canvasW - w) / 2;
				canvasY = (canvasH - h) / 2;
				thumb = image;
			}
			else {
				double a = (double)(w) / (double)width;
				double b = (double)(h) / (double)height;
				// La foto se escala
				if (a > b) {
					// La proporcion horizontal es mayor que la vertical
					thumb = new ImageIcon(
							image.getImage().getScaledInstance(width, -1, Image.SCALE_SMOOTH));
					canvasY = (canvasH - thumb.getImage().getHeight(null)) / 2;
				}
				else {
					// La proporcion vertical es mayor que la horizontal
					thumb = new ImageIcon(image.getImage().getScaledInstance(-1, height, Image.SCALE_SMOOTH));
					canvasX = (canvasW - thumb.getImage().getWidth(null)) / 2;
				}
			}

		}
		else if (dir == MAX_DIMENSIONS || dir == MIN_DIMENSIONS) {
			int w = image.getIconWidth();
			int h = image.getIconHeight();

			if (width > w && height > h) {
				// No se escala
				thumb = image;
			}
			else {
				double a = (double)(w) / (double)width;
				double b = (double)(h) / (double)height;
				// La foto se escala
				if (dir == MAX_DIMENSIONS) {
					if (a > b) {
						// La proporcion horizontal es mayor que la vertical
						thumb = new ImageIcon(
								image.getImage().getScaledInstance(width, -1, Image.SCALE_SMOOTH));
					}
					else {
						// La proporcion vertical es mayor que la horizontal
						thumb = new ImageIcon(image.getImage().getScaledInstance(-1, height, Image.SCALE_SMOOTH));
					}
				}
				else {
					if (a > b) {
						// La proporcion horizontal es mayor que la vertical
						thumb = new ImageIcon(
								image.getImage().getScaledInstance(-1, height, Image.SCALE_SMOOTH));
					}
					else {
						// La proporcion vertical es mayor que la horizontal
						thumb = new ImageIcon(image.getImage().getScaledInstance(width, -1, Image.SCALE_SMOOTH));
					}
				}
			}
			canvasW = thumb.getImage().getWidth(null);
			canvasH = thumb.getImage().getHeight(null);
		}
		BufferedImage bi = new BufferedImage(canvasW, canvasH, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.setColor(new Color(0xFAFAFA));
		g2d.fillRect(0, 0, canvasW, canvasH);
		g2d.drawImage(thumb.getImage(), canvasX, canvasY, null);
		g2d.dispose();
		return bi;
	}

	public Image getThumbnail(int width, int height, int dir, int scale) {
		if (dir == HORIZONTAL) {
			thumb = new ImageIcon(
					image.getImage().getScaledInstance(width, -1, scale));
		}
		else if (dir == VERTICAL) {
			thumb = new ImageIcon(
					image.getImage().getScaledInstance(-1, height, scale));
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
		ct.getThumbnail(100, 100, CreateThumbnail.HORIZONTAL);
		ct.saveThumbnail(new File("thumb.jpg"), CreateThumbnail.IMAGE_JPEG);
	}

}
