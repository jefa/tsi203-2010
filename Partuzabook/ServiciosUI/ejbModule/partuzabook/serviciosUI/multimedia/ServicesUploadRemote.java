package partuzabook.serviciosUI.multimedia;
import java.util.List;

import javax.ejb.Remote;

import partuzabook.datatypes.DataTypeFile;

@Remote
public interface ServicesUploadRemote {
	public List<String> uploadMultimedia(int eventID, String username, List<DataTypeFile> files);
	byte[] getMultimedia(int eventID, String username, int contentID);
	byte[] getMultimediaThumbnail(int eventID, String username, int contentID);
}
