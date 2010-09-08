package edu.tsi.Practico;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.annotation.Resource;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class Session1
 */
//@Resource (name="jdbc/PostgresDS")
@Stateless(name="Session1Remote")
public class Session1 implements Session1Remote {
	
	private DataSource postgresDS;
	
    /**
     * Default constructor. 
     */
    public Session1() {
    }

	@Override
	public String getMessage() {

		java.sql.Connection cnn = null;
		ResultSet rs = null;

		try {
			
			String msg = "hola mundo:::: ";
        	String compName = "java:/PostgresDS";
        	
        	Context initialContext = new InitialContext();        	
        	postgresDS = (DataSource) initialContext.lookup(compName);

        	cnn = postgresDS.getConnection();
			rs = cnn.createStatement().executeQuery("select count(*) cuenta from products");
			if (rs.next()){
				msg = msg + String.valueOf(rs.getInt(/*"cuenta"*/1));
			}
			
			return msg;
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error: " + e.getMessage(); 
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (cnn != null)
					cnn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.println(" ERROR AL CERRAR CONNECTION:: "+e.getMessage());
				return " ERROR AL CERRAR CONNECTION:: "+e.getMessage();
			}
		}
	}
    

}
