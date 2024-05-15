package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import database.*;
import model.*;

public class FoderplanController {
	private Foderplan fp;

	public FoderplanController() {
		fp = null;

	}

	/**
	 * registrerFoderplan metoden bliver kaldt med to localdates og en int. Der
	 * oprettes en ny medarbejder ved brug af en statisk metode fra
	 * MedarbejderController. instansvariabel fp bliver instanseret med @param og
	 * medarbejder m Metoden returnere en ny Foderplan
	 * 
	 * @param startDato, slutDato, hyppighed
	 * @return
	 * @Author gruppe 4
	 */
	public Foderplan registrerFoderplan(LocalDate startDato, LocalDate slutDato, int hyppighed) {
		if (startDato.isBefore(LocalDate.now()) || slutDato.isBefore(startDato)) {
			fp = null;
		} else if (!(hyppighed >= 1 && hyppighed <= 3)) {
			fp = null;
		} else {
			Medarbejder m = MedarbejderController.hentLoggetIndMedarbejder();
			fp = new Foderplan(startDato, slutDato, hyppighed, m);
		}
		return fp;
	}

	/**
 * Tilføjer en ny FoderLinje til foderplanen med det angivne foderNavn og mængde.
 * 
 * @param foderNavn navnet på fodret
 * @param mængde    mængden af fodret, der skal tilføjes
 * @return FoderLinje-objektet, der blev tilføjet, eller null, hvis fodret ikke blev fundet
 * @throws DataAccessException hvis der opstår en fejl ved adgang til data
 * @Author gruppe 4
	 */
	public FoderLinje tilføjFoder(String foderNavn, int mængde) {
		Foder f = null;
		FoderLinje fl = null;
		FoderController fc = new FoderController();
		if (mængde > 0) {
			f = fc.findFoderMedNavn(foderNavn);
			if (f != null) {
				fl = new FoderLinje(f, mængde);
				fp.tilføjFoderLinje(fl);
			}
		}
		return fl;
	}

	/**
	 * Tilføjer en ny hest til foderplanen med det angivne hestCHR.
	 * 
	 * @param hestCHR et unikt identifikationsnummer for hesten
	 * @return Hest-objektet, der blev tilføjet
	 * @throws SQLException hvis der opstår en fejl ved adgang til databasen
	 * @Author gruppe 4
	 */
	public Hest tilføjHest(int hestCHR) throws SQLException {
		Hest h = null;
		HestController hc = new HestController();
		h = hc.findHestMedCHR(hestCHR);
		fp.tilføjHest(h);
		return h;
	}

	/**
	 * Godkender foderplanen ved at gemme den i databasen.
	 * 
	 * @return Den godkendte foderplan
	 * @throws DataAccessException hvis der opstår en fejl ved adgang til databasen
	 * @Author gruppe 4
	 */
	public Foderplan godkendFoderplan() throws DataAccessException {
		try {
			if (fp.getFoderLinje() == null) {
				return null;
			} else {
				FoderplanDBIF fpDB = new FoderplanDB();
				fp = fpDB.gemFoderplan(fp);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e, "Couldn't save");
		}
	
		return fp;
	}

}
