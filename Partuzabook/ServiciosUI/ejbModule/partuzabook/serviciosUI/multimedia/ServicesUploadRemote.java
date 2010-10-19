package partuzabook.serviciosUI.multimedia;
import java.util.List;

import javax.ejb.Remote;

import partuzabook.datatypes.DataTypeFile;

@Remote
public interface ServicesUploadRemote {
	public List<String> uploadMultimedia(int eventID, String username, List<DataTypeFile> files);
	byte[] getContent(String username, int contentID, int thumbnail);
	byte[] getUserAvatar(String usrID);
}
