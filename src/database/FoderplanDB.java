package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.MedarbejderController;
import model.*;

public class FoderplanDB implements FoderplanDBIF {
	private static final String insertFoderplanQ = "INSERT INTO foderplan (hyppighed, startDato, slutDato, "
			+ "medarbejderID, hestID) VALUES (?, ?, ?, ?, ?)";
	private PreparedStatement insertFoderplan;
	private static final String insertFoderlinjeQ = "INSERT INTO foderLinje (foderID, "
			+ "foderplanID, mængde) VALUES (?, ?, ?)";
	private PreparedStatement insertFoderlinje;
	private static final String selectAllQ = "SELECT * FROM foderplan";
    private static final String selectByIdQ = selectAllQ + " where foderplanID = ?";
    private PreparedStatement selectAll; 
	private PreparedStatement selectById;
	
	public FoderplanDB() throws SQLException {
		try {
        DBConnection.getInstance().startTransaction();
    } catch (DataAccessException e1) {
        e1.printStackTrace();
    }
		try {
			insertFoderplan = DBConnection.getInstance().getConnection()
			        .prepareStatement(insertFoderplanQ, PreparedStatement.RETURN_GENERATED_KEYS);
			insertFoderlinje = DBConnection.getInstance().getConnection()
			        .prepareStatement(insertFoderlinjeQ, PreparedStatement.RETURN_GENERATED_KEYS);
			selectAll = DBConnection.getInstance().getConnection()
			        .prepareStatement(selectAllQ);
			selectById = DBConnection.getInstance().getConnection()
			        .prepareStatement(selectByIdQ);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Gemmer en Foderplan i databasen.
	 * 
	 * @param fp Foderplan-objektet, der skal gemmes
	 * @return det gemte Foderplan-objekt med tildelt ID
	 * @throws DataAccessException hvis der opstår en fejl under dataadgangen
	 * @Author gruppe 4
	 */
	@Override
	public Foderplan gemFoderplan(Foderplan fp) throws DataAccessException {
	    try {
	        DBConnection.getInstance().startTransaction();

	        // Converter localdate til SQL~date.
	        Date startDato = Date.valueOf(fp.getStartDato());
	        Date slutDato = Date.valueOf(fp.getSlutDato());
	        
	        insertFoderplan.setInt(1, fp.getHyppighed());
	        insertFoderplan.setDate(2, startDato);
	        insertFoderplan.setDate(3, slutDato);
	        insertFoderplan.setInt(4, fp.getMedarbejder().getID());
	        insertFoderplan.setInt(5, fp.getHest().getId());

	        int id = DBConnection.getInstance().executeInsertWithIdentity(insertFoderplan);
	        fp.setID(id);

	        for (FoderLinje fl : fp.getFoderLinje()) {
	            insertFoderlinje.setInt(1, fl.getFoder().getFoderID());
	            insertFoderlinje.setInt(2, fp.getID());
	            insertFoderlinje.setInt(3, fl.getMængde());
	            DBConnection.getInstance().executeInsertWithIdentity(insertFoderlinje);
	        }

	        DBConnection.getInstance().commitTransaction();
	      
	    } catch (SQLException e) {
	        DBConnection.getInstance().rollbackTransaction();
	        throw new DataAccessException(e, "An error occurred while saving foderplan");
	    }

	    return fp;
	}

	/**
	 * Henter alle foderplaner fra databasen.
	 * 
	 * @return en liste over alle foderplaner
	 * @throws DataAccessException hvis der opstår en fejl under dataadgangen
	 * @Author gruppe 4
	 */
	@Override
	public List<Foderplan> hentFoderplaner() throws DataAccessException {
	    try {
	        DBConnection.getInstance().startTransaction();
	        
	        ResultSet rs = selectAll.executeQuery();
	        List<Foderplan> res = buildObjects(rs);
	        
	        DBConnection.getInstance().commitTransaction();
	    
	        return res;
	    } catch (SQLException e) {
	        DBConnection.getInstance().rollbackTransaction();
	       
	        throw new DataAccessException(e, "Could not find any foderplaner");
	    }
	}
	/**
	 * Konverterer et ResultSet med foderplaner til en liste af Foderplan-objekter.
	 * 
	 * @param rs ResultSet indeholdende foderplan-data
	 * @return en liste over Foderplan-objekter
	 * @throws SQLException hvis der opstår en fejl under SQL-udtrækningen
	 * @Author gruppe 4
	 */
	private List<Foderplan> buildObjects(ResultSet rs) throws SQLException {
	    List<Foderplan> res = new ArrayList<>();
	    while (rs.next()) {
	        res.add(buildObject(rs));
	    }
	    return res;
	}

	/**
	 * Henter en foderplan fra databasen baseret på dens ID.
	 * 
	 * @param id ID'et for den ønskede foderplan
	 * @return den fundne foderplan eller null, hvis ingen foderplan blev fundet med det angivne ID
	 * @throws DataAccessException hvis der opstår en fejl under dataadgangen
	 * @Author gruppe 4
	 */
	@Override
	public Foderplan hentFoderplanMedID(int id) throws DataAccessException {
	    try {
	        DBConnection.getInstance().startTransaction();
	        selectById.setInt(1, id);
	        ResultSet rs = selectById.executeQuery();
	        Foderplan res = null;
	        if (rs.next()) {
	            res = buildObject(rs);
	        }
	        DBConnection.getInstance().commitTransaction();
	        
	        return res;
	    } catch (SQLException e) {
	        DBConnection.getInstance().rollbackTransaction();
	      
	        throw new DataAccessException(e, "Could not find any foderplaner");
	    }
	}
	/**
	 * Opretter et Foderplan-objekt baseret på data fra en ResultSet.
	 * 
	 * @param rs ResultSet, der indeholder data til at opbygge et Foderplan-objekt
	 * @return det oprettede Foderplan-objekt
	 * @throws SQLException hvis der opstår en fejl under SQL-operationer
	 * @Author gruppe 4
	 */
	private Foderplan buildObject(ResultSet rs) throws SQLException {
	    LocalDate startDato = rs.getDate("startDato").toLocalDate();
	    LocalDate slutDato = rs.getDate("slutDato").toLocalDate();

	    Foderplan fp = new Foderplan(
	            rs.getInt("foderplanID"),
	            startDato,
	            slutDato,
	            rs.getInt("hyppighed")
	    );
	    return fp;
	}
}