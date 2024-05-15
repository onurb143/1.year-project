package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.*;
import database.*;
import model.*;

class FoderplanTest {
	private static final LocalDate STARTDATO = LocalDate.now();
	private static final LocalDate SLUTDATO = STARTDATO.plusWeeks(4);
	private FoderplanDBIF fpdb;
	private Foderplan fp;
	FoderplanController fpc;

	@BeforeEach
	void setUp() throws DataAccessException, SQLException {
		fpc = new FoderplanController();
		fpdb = new FoderplanDB();
		fp = null;
	}

	@AfterEach
	void tearDown() throws DataAccessException, SQLException {
	}

	@Test
	void opretNyFoderplan() throws DataAccessException, SQLException {
		/*----------------------- 1 --------------------------*/

		fp = fpc.registrerFoderplan(STARTDATO, SLUTDATO, 3);

		/*----------------------- 2 --------------------------*/
		fpc.tilføjFoder("æble", 6);
		/*----------------------- 3 --------------------------*/
		fpc.tilføjHest(1);
		/*----------------------- 4 --------------------------*/
		fpc.godkendFoderplan();
		/*---------------------- Test ------------------------*/
		assertTrue(fp.getID() > 0);
	}

	@Test
	void getFoderplandMedID() throws DataAccessException, SQLException {
		int id = 153;
		fp = fpdb.hentFoderplanMedID(id);
		assertEquals(fp.getID(), id);
	}

	@Test
	void getAlleFoderplaner() throws DataAccessException, SQLException {
		List<Foderplan> fps = fpdb.hentFoderplaner();
		assertFalse(fps.size() == 0);
	}

	@Test
	void allValidShouldRegistrerFoderplan() {

		// Arrange
		final LocalDate STARTDATO = LocalDate.now();
		final LocalDate SLUTDATO = STARTDATO.plusWeeks(4);
		final int HYPPIGHED = 3;

		// Act
		fp = fpc.registrerFoderplan(STARTDATO, SLUTDATO, HYPPIGHED);

		// Assert
		assertNotNull(fp, "Foderplan laves");
		assertEquals(STARTDATO, fp.getStartDato(), "Startdato skal matche input");
		assertEquals(SLUTDATO, fp.getSlutDato(), "Slutdato skale matche input");
		assertEquals(HYPPIGHED, fp.getHyppighed(), "Hypplighed skal matche input");

	}

	@Test
	void invalidStartDatoShouldFail() {

		// Arrange
		final LocalDate STARTDATO = LocalDate.now().minusDays(1);
		final LocalDate SLUTDATO = STARTDATO.plusWeeks(4);
		final int HYPPIGHED = 3;

		// Act
		fp = fpc.registrerFoderplan(STARTDATO, SLUTDATO, HYPPIGHED);

		// Assert
		assertNull(fp, "Foderplan skal ikke laves");
	}

	@Test
	void invalidSlutDatoShouldFail() {

		// Arrange
		final LocalDate STARTDATO = LocalDate.now();
		final LocalDate SLUTDATO = STARTDATO.minusDays(1);
		final int HYPPIGHED = 3;

		// Act
		fp = fpc.registrerFoderplan(STARTDATO, SLUTDATO, HYPPIGHED);

		// Assert
		assertNull(fp, "Foderplan skal ikke laves");
	}

	@Test
	void hyppighedOverBoundaryShouldFail() {

		// Arrange
		final LocalDate STARTDATO = LocalDate.now();
		final LocalDate SLUTDATO = STARTDATO.plusWeeks(4);
		final int HYPPIGHED = 4;

		// Act
		fp = fpc.registrerFoderplan(STARTDATO, SLUTDATO, HYPPIGHED);

		// Assert
		assertNull(fp, "Foderplan skal ikke laves");

	}

	@Test
	void hyppighedUnderBoundaryShouldFail() {

		// Arrange
		final LocalDate STARTDATO = LocalDate.now();
		final LocalDate SLUTDATO = STARTDATO.plusWeeks(4);
		final int HYPPIGHED = 0;

		// Act
		fp = fpc.registrerFoderplan(STARTDATO, SLUTDATO, HYPPIGHED);

		// Assert
		assertNull(fp, "Foderplan skal ikke laves");

	}

	@Test
	void hyppighedOnBoundaryShouldNotFail() {

		// Arrange
		final LocalDate STARTDATO = LocalDate.now();
		final LocalDate SLUTDATO = STARTDATO.plusWeeks(4);
		final int HYPPIGHED = 2;

		// Act
		fp = fpc.registrerFoderplan(STARTDATO, SLUTDATO, HYPPIGHED);

		// Assert
		assertNotNull(fp, "Foderplan laves");
		assertEquals(STARTDATO, fp.getStartDato(), "Startdato skal matche input");
		assertEquals(SLUTDATO, fp.getSlutDato(), "Slutdato skale matche input");
		assertEquals(HYPPIGHED, fp.getHyppighed(), "Hypplighed skal matche input");

	}

	@Test
	void allValidShouldTilføjFoder() {

		// Arrange
		final String foderNavn = "gulerod";
		final int mængde = 5;
		final LocalDate STARTDATO = LocalDate.now();
		final LocalDate SLUTDATO = STARTDATO.plusWeeks(4);
		final int HYPPIGHED = 3;

		// Act
		fp = fpc.registrerFoderplan(STARTDATO, SLUTDATO, HYPPIGHED);
		FoderLinje fl = fpc.tilføjFoder(foderNavn, mængde);
		
		// Assert
		assertTrue(!fp.getFoderLinje().isEmpty());
		assertTrue(fp.getFoderLinje().size() == 1);
		assertTrue(fp.getFoderLinje().get(0).getFoder().getFoderNavn().equals(foderNavn));

	}
	
	@Test
	void invalidFoderNavnShouldReturnNull() {

		// Arrange
		final String foderNavn = "hånd";
		final int mængde = 5;
		final LocalDate STARTDATO = LocalDate.now();
		final LocalDate SLUTDATO = STARTDATO.plusWeeks(4);
		final int HYPPIGHED = 3;

		// Act
		fp = fpc.registrerFoderplan(STARTDATO, SLUTDATO, HYPPIGHED);
	
		// Assert
		assertNull(fpc.tilføjFoder(foderNavn, mængde));
	}
	

}
