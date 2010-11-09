package partuzabook.datos.persistencia.filesystem;
import javax.ejb.Local;

@Local
public interface FileSystemLocal {
	String writeFile(byte[] data, String mimeType, String dir);
	byte[] readFile(String filename, String thumbnail);
	String saveExternalFile(String dirLocal, String dirWeb);
	public void deleteFile(String fileName);
}
