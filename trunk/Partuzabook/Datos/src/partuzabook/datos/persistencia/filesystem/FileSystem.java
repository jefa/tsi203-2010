package partuzabook.datos.persistencia.filesystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.SystemUtils;

import com.sun.tools.ws.wsdl.document.jaxws.Exception;

 

import javax.ejb.Stateless;

import partuzabook.utils.CreateThumbnail;

/**
 * Session Bean implementation class FileSystem
 */
@Stateless
public class FileSystem implements FileSystemLocal {

    /**
     * Default constructor. 
     */
    public FileSystem() {
    }
    
    private String getBasePath() throws ConfigurationException {
    	Configuration config = new PropertiesConfiguration("settings.properties");
    	if (SystemUtils.IS_OS_WINDOWS)
    			return config.getString("imagesPathWindows") + "/Partuzabook/";
    	
    	if (SystemUtils.IS_OS_LINUX)
    		return config.getString("imagesPathLinux") + "/Partuzabook/";
    	
    	// TODO: aca seria mas prolijo tirar un excepcion
    	return null;
    }
    
    public String writeFile(byte[] data, String mimeType, String dir) {
		try {
	    	String uuid = java.util.UUID.randomUUID().toString();
	    	String path = getBasePath(); 
	    	String extension = "." + mimeType.split("/")[1];
	    	extension.replace("unknown", "jpeg");
	    	new File(path + dir).mkdirs();
	    	FileOutputStream fstream = new FileOutputStream(path + dir + uuid + extension);
	    	fstream.write(data);
	    	fstream.close();
	    	return dir + uuid + extension;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    public byte[] readFile(String filename) {
		try {
	    	File f = new File(getBasePath() + filename);
	    	int len = (int)f.length();
	    	FileInputStream fstream = new FileInputStream(f);
	    	byte[] data = new byte[len]; 
	    	fstream.read(data, 0, len);
	    	fstream.close();
	    	return data;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    public byte[] getThumbnail(String filename) {
		try {
			int lastSlash = filename.lastIndexOf("/") + 1;
			String prefix = filename.substring(0, lastSlash);
			String postfix = filename.substring(lastSlash, filename.length());
			File f = new File(getBasePath() + prefix + "thb_" + postfix);
			if (!f.exists()) {
				CreateThumbnail ct = new CreateThumbnail(getBasePath() + filename);
				ct.getThumbnail(100, CreateThumbnail.HORIZONTAL);
				ct.saveThumbnail(new File(getBasePath() + prefix + "thb_" + postfix), CreateThumbnail.IMAGE_JPG);
			}
			f = new File(getBasePath() + prefix + "thb_" + postfix);
	    	int len = (int)f.length();
	    	FileInputStream fstream = new FileInputStream(f);
	    	byte[] data = new byte[len]; 
	    	fstream.read(data, 0, len);
	    	fstream.close();
	    	return data;
		}
		catch (FileNotFoundException e) {
			//e.printStackTrace();
		}
		catch (IOException e) {
			//e.printStackTrace();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
}