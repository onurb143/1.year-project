package database;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

import model.Foder;
import model.Hest;

/**
 * This interface provides methods for accessing and manipulating Hest objects in the database.
 *
 * @Author gruppe 4
 */
public interface HestDBIF {
    /**
     * Finds a Hest object in the database based on the provided hestCHR.
     *
     * @param hestCHR the CHR (Chip ID) of the horse
     * @return the found Hest object, or null if not found
     * @throws DataAccessException if an error occurs during data access
     */
    Hest findHestMedCHR(int hestCHR) throws DataAccessException;

    /**
     * Retrieves all Hest objects from the database.
     *
     * @return a list of all Hest objects
     * @throws DataAccessException if an error occurs during data access
     */
    List<Hest> hentAlleHeste() throws DataAccessException;
}
