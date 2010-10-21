package partuzabook.serviciosUI.multimedia;
import java.util.List;

import javax.ejb.Remote;

import partuzabook.datatypes.DataTypeFile;

@Remote
public interface ServicesUploadRemote {
	public List<String> uploadMultimedia(int eventID, String username, List<DataTypeFile> files);
	public byte[] getContent(String username, int contentID, int thumbnail);
	public byte[] getUserAvatar(String usrID, int thumbnail);
	public byte[] getPublicContent(String type, int pos, int thumbnail);
	public byte[] getPublicAvatar(String type, int pos, int thumbnail);
}
