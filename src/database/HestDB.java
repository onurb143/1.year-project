package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Foderplan;
import model.Hest;


public class HestDB implements HestDBIF{
	private static final String selectAllQ = "SELECT * FROM hest";
    private static final String selectByHestCHRQ = selectAllQ + " where hestCHR = ?";
    private PreparedStatement selectAll; 
	private PreparedStatement selectByHestCHR;
	
	public HestDB() throws SQLException {
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
			selectByHestCHR = DBConnection.getInstance().getConnection()
					.prepareStatement(selectByHestCHRQ);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
     * Finds a Hest object in the database based on the provided hestCHR.
     *
     * @param hestCHR the CHR (Chip ID) of the horse
     * @return the found Hest object, or null if not found
     * @throws DataAccessException if an error occurs during data access
     */
    @Override
    public Hest findHestMedCHR(int hestCHR) throws DataAccessException {
        try {
            selectByHestCHR.setInt(1, hestCHR);
            ResultSet rs = selectByHestCHR.executeQuery();
            Hest h = null;
            if (rs.next()) {
                h = buildObject(rs);
            }
            return h;
        } catch (SQLException e) {
            throw new DataAccessException(e, "Could not find by hestCHR = " + hestCHR);
        }
    }

    /**
     * Builds a Hest object from the provided ResultSet.
     *
     * @param rs the ResultSet containing the Hest data
     * @return the constructed Hest object
     * @throws SQLException if an SQL error occurs
     */
    public Hest buildObject(ResultSet rs) throws SQLException {
        LocalDate sidstVaccineret = rs.getDate("sidstVaccineret").toLocalDate();
        LocalDate sidstSkiftetSko = rs.getDate("sidstSkiftetSko").toLocalDate();

        Hest h = new Hest(
                rs.getInt("hestID"),
                rs.getInt("hestCHR"),
                rs.getString("navn"),
                rs.getString("født"),
                rs.getString("køn"),
                rs.getString("farve"),
                rs.getString("far"),
                rs.getString("mor"),
                sidstVaccineret,
                sidstSkiftetSko
        );
        return h;
    }

    /**
     * Retrieves all Hest objects from the database.
     *
     * @return a list of all Hest objects
     * @throws DataAccessException if an error occurs during data access
     */
    @Override
    public List<Hest> hentAlleHeste() throws DataAccessException {
        try {
            DBConnection.getInstance().startTransaction();

            ResultSet rs = selectAll.executeQuery();
            List<Hest> res = buildObjects(rs);

            DBConnection.getInstance().commitTransaction();

            return res;
        } catch (SQLException e) {
            DBConnection.getInstance().rollbackTransaction();
            throw new DataAccessException(e, "Could not find any horses");
        }
    }

    /**
     * Builds a list of Hest objects from the provided ResultSet.
     *
     * @param rs the ResultSet containing the Hest data
     * @return a list of constructed Hest objects
     * @throws SQLException if an SQL error occurs
     */
    public List<Hest> buildObjects(ResultSet rs) throws SQLException {
        List<Hest> res = new ArrayList<>();
        while (rs.next()) {
            res.add(buildObject(rs));
        }
        return res;
    }
}