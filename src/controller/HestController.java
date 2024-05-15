package controller;

import java.sql.SQLException;

import database.DataAccessException;
import database.HestDB;
import database.HestDBIF;
import model.Hest;

public class HestController {
	private Hest h;
	
	
	public HestController() {
		 h = null;
	}
	/**
	 * Finder og returnerer en hest baseret på det angivne CHR (Central Horse Registry) nummer.
	 * Hvis der opstår en SQLException under databasen, udskrives fejlmeddelelsen.
	 * 
	 * @param CHR det unikke CHR-nummer, der identificerer hesten
	 * @return den fundne hest eller null, hvis ingen hest blev fundet med det angivne CHR-nummer
	 * @throws SQLException hvis der opstår en fejl under databasen
	 * @Author gruppe 4
	 */
	public Hest findHestMedCHR(int CHR) throws SQLException {
		HestDBIF hdb = new HestDB();
		try {
			h = hdb.findHestMedCHR(CHR);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return h;
	}
}


