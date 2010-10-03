package partuzabook.serviciosUI.multimedia;
import java.util.List;

import javax.ejb.Remote;

import partuzabook.datatypes.DatatypeUploadedContent;

@Remote
public interface ServicesMultimediaRemote {

	boolean uploadContent(String eventID, List<DatatypeUploadedContent> pics);
}
