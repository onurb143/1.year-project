package controller;

import model.Medarbejder;

public class MedarbejderController {
	private static Medarbejder m;

	/**
	 * Returnerer den medarbejder, der er logget ind i systemet.
	 * Bemærk: Denne metode er midlertidig og returnerer en hårdkodet medarbejder.
	 * Når login-funktionaliteten er implementeret, skal denne metode ændres til at returnere den faktiske logget ind medarbejder.
	 * 
	 * @return den logget ind medarbejder (midlertidigt hårdkodet)
	 * @Author gruppe 4
	 */
	public static Medarbejder hentLoggetIndMedarbejder() {
		m = new Medarbejder(1, "Ole", "12345678", "ole@thomsen.dk", "12121212");
		return m; //Det skal laves om, når login er lavet.
	}
}




