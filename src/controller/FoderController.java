package controller;

import database.DataAccessException;
import database.FoderDB;
import database.FoderDBIF;
import model.Foder;

public class FoderController {
	
	/**
	 * Finder og returnerer et foderobjekt med det angivne foderNavn.
	 * Hvis der opstår en fejl under databasen, udskrives fejlmeddelelsen.
	 * 
	 * @param foderNavn navnet på foderet, der skal findes
	 * @return det fundne foderobjekt, eller null hvis foderet ikke blev fundet
	 * @Author gruppe 4
	 */
	public Foder findFoderMedNavn(String foderNavn) {
		Foder f = null;
		FoderDBIF fdb = null;
			try {
				fdb = new FoderDB();
			} catch (DataAccessException e) {

				e.printStackTrace();
			}
			try {
				f = fdb.findFoderMedNavn(foderNavn);
			} catch (DataAccessException e) {

				e.printStackTrace();
			}
		return f;
	}
}