package partuzabook.datos.persistencia.filesystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class FileSystem
 */
@Stateless
public class FileSystem implements FileSystemLocal {

    /**
     * Default constructor. 
     */
    public FileSystem() {
        // TODO Auto-generated constructor stub
    }
    
    private String getBasePath() throws IOException {
		return new java.io.File(".").getCanonicalPath() + "/Partuzabook/";
    }
    
    public String writeFile(byte[] data, String mimeType, String dir, String prefix) {
    	MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
	    	String md5 = byteArrayToHexString(md.digest(data));
	    	String path = getBasePath(); 
	    	String extension = "." + mimeType.split("/")[1];
	    	extension.replace("unknown", "jpeg");
	    	new File(path + dir).mkdirs();
	    	FileOutputStream fstream = new FileOutputStream(path + dir + prefix + md5 + extension);
	    	fstream.write(data);
	    	fstream.close();
	    	return dir + prefix + md5 + extension;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

    private String byteArrayToHexString(byte[] input) {
    	String output = "";
    	for (int i = 0; i < input.length; i++) {
    		output += Integer.toHexString(input[i] & 0xF0) + Integer.toHexString(input[i] & 0x0F);
		}
    	return output;
    }
}
