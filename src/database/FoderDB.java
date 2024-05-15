package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Foder;
import model.Foderplan;

public class FoderDB implements FoderDBIF {
	private static final String selectAllQ = "SELECT * FROM foder";
    private static final String selectByNameQ = selectAllQ + " where foderNavn = ?";
    private PreparedStatement selectAll; 
	private PreparedStatement selectByName;
	
	public FoderDB() throws DataAccessException {
		try {
			selectAll = DBConnection.getInstance().getConnection()
			        .prepareStatement(selectAllQ);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			selectByName = DBConnection.getInstance().getConnection()
			        .prepareStatement(selectByNameQ);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Finder og returnerer et Foder-objekt med det angivne foderNavn.
	 * 
	 * @param foderNavn navnet på det ønskede foder
	 * @return Foder-objektet med det angivne foderNavn, eller null, hvis foderet ikke findes
	 * @throws DataAccessException hvis der opstår en fejl under adgangen til databasen
	 * @Author gruppe 4
	 */

	@Override
	public Foder findFoderMedNavn(String foderNavn) throws DataAccessException {
		try {
			selectByName.setString(1, foderNavn);
			ResultSet rs = selectByName.executeQuery();
			Foder f = null;
			if (rs.next()) {
				f = buildObject(rs);
			}
			return f;
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not find by name = " + foderNavn);
		}
	}
	/**
	 * Opretter og returnerer et Foder-objekt baseret på dataene i ResultSet.
	 * 
	 * @param rs ResultSet indeholdende data til oprettelse af Foder-objektet
	 * @return det oprettede Foder-objekt
	 * @throws SQLException hvis der opstår en fejl under adgangen til ResultSet
	 * @Author gruppe 4
	 */

	private Foder buildObject(ResultSet rs) throws SQLException {
		LocalDate localDate = rs.getDate("dato").toLocalDate();
		
		Foder f = new Foder(
				rs.getInt("foderID"),
				rs.getString("foderNavn"),
				rs.getDouble("pris"),
				rs.getString("foderType"),
				rs.getString("beskrivelses"),
				rs.getString("leverandør"),
				rs.getString("ernæringsindhold"),
				rs.getString("fabrikant"),
				rs.getDouble("vægt"),
				localDate,
				rs.getInt("foderBeholdning")
				);
		return f;
	}
	
	/**
	 * Henter alle foderobjekter fra databasen og returnerer dem som en liste.
	 * 
	 * @return en liste over alle foderobjekter
	 * @throws DataAccessException hvis der opstår en fejl under adgangen til data eller transaktionshåndtering
	 * @Author gruppe 4
	 */
	
	public List<Foder> hentAltFoder() throws DataAccessException {
		try {
	        DBConnection.getInstance().startTransaction();
	        ResultSet rs = selectAll.executeQuery();
	        List<Foder> res = buildObjects(rs);
	        DBConnection.getInstance().commitTransaction();
	    
	        return res;
	    } catch (SQLException e) {
	        DBConnection.getInstance().rollbackTransaction();
	       
	        throw new DataAccessException(e, "Could not find any foderplaner");
	    }
	}
	/**
	 * Konverterer en ResultSet med foderdata til en liste af Foder-objekter.
	 * 
	 * @param rs ResultSet, der indeholder foderdata
	 * @return en liste over Foder-objekter
	 * @throws SQLException hvis der opstår en SQL-relateret fejl under konverteringen
	 * @Author gruppe 4
	 */

	private List<Foder> buildObjects(ResultSet rs) throws SQLException {
		List<Foder> res = new ArrayList<>();
	    while (rs.next()) {
	        res.add(buildObject(rs));
	    }
	    return res;
	}
}
