package partuzabook.serviciosUI.multimedia;
import java.util.List;

import javax.ejb.Remote;

import partuzabook.datatypes.DataTypeFile;

@Remote
public interface ServicesUploadRemote {
	List<String> uploadMultimedia(int eventID, String username, List<DataTypeFile> files);
}
