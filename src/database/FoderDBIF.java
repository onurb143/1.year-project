package database;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

import model.Foder;

/**
 * Interface til databasen for Foder-entiteten.
 */
public interface FoderDBIF {
    
    /**
     * Finder et Foder-objekt med det angivne foderNavn.
     * 
     * @param foderNavn navnet på det foder, der skal findes
     * @return det fundne Foder-objekt, eller null hvis det ikke findes
     * @throws DataAccessException hvis der opstår en fejl under dataadgangen
     * @Author gruppe 4
     */
    Foder findFoderMedNavn(String foderNavn) throws DataAccessException;
    
    /**
     * Henter alle Foder-objekter fra databasen.
     * 
     * @return en liste over alle Foder-objekter
     * @throws DataAccessException hvis der opstår en fejl under dataadgangen
     * @Author gruppe 4
     */
    List<Foder> hentAltFoder() throws DataAccessException;
}
