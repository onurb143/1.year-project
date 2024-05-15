package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import controller.HestController;
import model.Hest;

class HestTest {

	@Test
	void test() throws SQLException {
		Hest h = null;
		HestController hc = new HestController();
		h = hc.findHestMedCHR(3);
		System.out.println(h.getId());
		assertTrue(h.getId() == 9);
	}
}