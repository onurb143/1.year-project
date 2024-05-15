package database;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

import model.Foderplan;

/**
 * Dette interface definerer metoder til håndtering af Foderplan-objekter i databasen.
 * 
 * @Author gruppe 4
 */
public interface FoderplanDBIF {
    /**
     * Gemmer en Foderplan i databasen.
     * 
     * @param fp Foderplan-objektet, der skal gemmes
     * @return den gemte Foderplan
     * @throws DataAccessException hvis der opstår en fejl under dataadgang
     */
    Foderplan gemFoderplan(Foderplan fp) throws DataAccessException;
    
    /**
     * Henter alle Foderplaner fra databasen.
     * 
     * @return en liste over alle Foderplaner
     * @throws DataAccessException hvis der opstår en fejl under dataadgang
     */
    List<Foderplan> hentFoderplaner() throws DataAccessException;
    
    /**
     * Henter en Foderplan fra databasen baseret på ID.
     * 
     * @param id ID'et for den ønskede Foderplan
     * @return den fundne Foderplan
     * @throws DataAccessException hvis der opstår en fejl under dataadgang
     */
    Foderplan hentFoderplanMedID(int id) throws DataAccessException;
}
