package partuzabook.datos.persistencia.filesystem;
import javax.ejb.Local;

@Local
public interface FileSystemLocal {
	String writeFile(byte[] data, String mimeType, String dir, String prefix);
	byte[] readFile(String filename);
}
