package partuzabook.datos.persistencia.DAO;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.Rating;
import partuzabook.datos.persistencia.beans.RatingPK;

@Local
public interface RatingDAO extends Dao<RatingPK, Rating>{
	
	public int getAverageRatingOfContent(int contentID);
	
	
}
