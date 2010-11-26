package partuzabook.datos.persistencia.filesystem;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.imageio.ImageIO;

import org.apache.commons.lang.SystemUtils;

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
			extension = extension.replace("unknown", "jpg");
			new File(path + dir).mkdirs();
			FileOutputStream fstream = new FileOutputStream(path + dir + uuid + "tmp" 
					+ extension);
			fstream.write(data);
			fstream.close();
			
			if (mimeType.contains("image")) {
				data = getThumbnail(dir + uuid + "tmp" + extension , "M960,600");
				fstream = new FileOutputStream(path + dir + uuid 
						+ extension);
				fstream.write(data);
				fstream.close();
			}
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
			int dir = 0;
			if (thumbnailSize.charAt(0) >= 48 && thumbnailSize.charAt(0) <= 57) {
				thumbnailSize = "S" + thumbnailSize;
			}
			String tipo = thumbnailSize.substring(0,1);
			String[] params = thumbnailSize.substring(1).split(",");

			int width = Integer.parseInt(params[0]);
			int height = 0;

			if (tipo.equals("F")) {
				height = Integer.parseInt(params[1]);
				dir = CreateThumbnail.FIXED_SIZE_SCALE_DOWN_ONLY;
			}
			else if (tipo.equals("M")) {
				height = Integer.parseInt(params[1]);
				dir = CreateThumbnail.MAX_DIMENSIONS;
			}
			else if (tipo.equals("m")) {
				height = Integer.parseInt(params[1]);
				dir = CreateThumbnail.MIN_DIMENSIONS;
			}
			else {
				height = width;
				if (tipo.equals("H")) {
					dir = CreateThumbnail.HORIZONTAL;
				}
				else if (tipo.equals("V")) {
					dir = CreateThumbnail.VERTICAL;
				}
				else if (tipo.equals("S")) {
					dir = CreateThumbnail.CLIP_AND_SCALE;
				}
			}

			if (checkExists.exists()) {
				bi = ct.getThumbnail(width, height, dir);
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

	public String saveExternalFile(String dirlocal, String dirWeb) {

		try {
			String uuid = java.util.UUID.randomUUID().toString();
			String path = getBasePath();
			int ind = dirWeb.split("\\.").length - 1;
			String mime = dirWeb.split("\\.")[ind];
			String extension = ".jpg";

			new File(path + dirlocal).mkdirs();


			URL url = new URL(dirWeb);
			URLConnection urlCon = url.openConnection();

			InputStream is = urlCon.getInputStream();
			FileOutputStream fos = new FileOutputStream(path + dirlocal + uuid + extension);

			// Lectura de la foto de la web y escritura en fichero local
			byte[] array = new byte[1000];
			int leido = is.read(array);
			while (leido > 0) {
				fos.write(array, 0, leido);
				leido = is.read(array);
			}

			is.close();
			fos.close();

			return dirlocal + uuid + extension;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public void deleteFile(String fileName) {
		try {
			File f = new File(getBasePath() + fileName);
			if(f.exists())
				f.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
