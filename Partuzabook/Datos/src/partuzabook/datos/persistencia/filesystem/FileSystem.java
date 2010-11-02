package partuzabook.datos.persistencia.filesystem;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.lang.SystemUtils;

import java.util.Properties;

import javax.ejb.Stateless;
import javax.imageio.ImageIO;

import partuzabook.utils.CreateThumbnail;

/**
 * Session Bean implementation class FileSystem
 */
@Stateless
public class FileSystem implements FileSystemLocal {

	private String basePath = null;

	/**
	 * Default constructor.
	 */
	public FileSystem() {
	}

	private String getBasePath() throws IOException {

		if (basePath == null) {
			Properties config = new Properties();
			config.load(this.getClass().getClassLoader()
					.getResourceAsStream("/settings.properties"));

			// Configuration config = new
			// PropertiesConfiguration("settings.properties");
			if (SystemUtils.IS_OS_WINDOWS)
				// return config.getString("imagesPathWindows") +
				// "/Partuzabook/";
				this.basePath = config.getProperty("imagesPathWindows")
						+ "/Partuzabook/";

			if (SystemUtils.IS_OS_LINUX)
				// return config.getString("imagesPathLinux") + "/Partuzabook/";
				this.basePath = config.getProperty("imagesPathLinux")
						+ "/Partuzabook/";

			// TODO: aca seria mas prolijo tirar un excepcion

			// System.out.println("FileSystem.getBasePath(): Base path is "+this.basePath);
		}

		return basePath;

	}

	public String writeFile(byte[] data, String mimeType, String dir) {
		try {
			String uuid = java.util.UUID.randomUUID().toString();
			String path = getBasePath();
			String extension = "." + mimeType.split("/")[1];
			extension.replace("unknown", "jpeg");
			new File(path + dir).mkdirs();
			FileOutputStream fstream = new FileOutputStream(path + dir + uuid
					+ extension);
			fstream.write(data);
			fstream.close();
			return dir + uuid + extension;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public byte[] readFile(String filename, String thumbnail) {
		if (!thumbnail.isEmpty()) {
			return getThumbnail(filename, thumbnail);
		}
		try {
			File f = new File(getBasePath() + filename);
			int len = (int) f.length();
			FileInputStream fstream = new FileInputStream(f);
			byte[] data = new byte[len];
			fstream.read(data, 0, len);
			fstream.close();
			return data;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private byte[] getThumbnail(String filename, String thumbnailSize) {
		try {
			CreateThumbnail ct = new CreateThumbnail(getBasePath() + filename);
			File checkExists = new File(getBasePath() + filename);
			byte[] bytesOut = null;
			BufferedImage bi = null;
			int dir = CreateThumbnail.CLIP_AND_SCALE;
			String tipo = thumbnailSize.substring(0,1);
			int tamanio = Integer.parseInt(thumbnailSize.substring(1));
			
			if (tamanio == 1) tamanio = 50; // por si acaso en algunos lugares sigue diciendo 1
			
			if (tipo.equals("H")) dir = CreateThumbnail.HORIZONTAL;
			else if (tipo.equals("V")) dir = CreateThumbnail.VERTICAL;
			else tamanio = Integer.parseInt(thumbnailSize);
			
			if (checkExists.exists()) {
				Image img = ct.getThumbnail(tamanio,dir);
				bi = new BufferedImage(img.getWidth(null), img.getHeight(null),
						BufferedImage.TYPE_INT_RGB);
				Graphics2D g2d = bi.createGraphics();
				g2d.drawImage(img, 0, 0, null);
				g2d.dispose();
			} else {
				bi = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
			}

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpeg", baos);
			bytesOut = baos.toByteArray();
			return bytesOut;

		} catch (FileNotFoundException e) {
			// e.printStackTrace();
		} catch (IOException e) {
			// e.printStackTrace();
		}
		return null;
	}
}
