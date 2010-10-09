package partuzabook.serviciosUI.multimedia;
import java.util.List;

import javax.ejb.Remote;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeUploadedContent;
import partuzabook.datatypes.DatatypeUser;

@Remote
public interface ServicesMultimediaRemote {

	/**
	 * Returns a list of the best picture for each event, ordered by rating
	 */
	public List<DatatypeContent> getBestQualifiedPictures(int length);
	
	/**
	 * Returns  a list of the best qualified pictures in all the server
	 */
	public List<DatatypeContent> getMostCommentedPictures(int length);
	
	/**
	 * Returns  a list of the best qualified pictures in all the server
	 */
	public List<DatatypeUser> getMostTagged();
}
