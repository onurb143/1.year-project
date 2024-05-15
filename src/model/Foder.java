package model;

import java.time.LocalDate;

public class Foder {
	private int foderID;
	private String foderNavn;
	private double pris;
	private String foderType;
	private String beskrivelse;
	private String leverandør;
	private String ernæringsindhold;
	private String fabrikant;
	private double vægt;
	private LocalDate dato;
	private int foderBeholdning;
	
	// for at hente foder fra database
	public Foder(int foderID, String foderNavn, double pris, String foderType, String beskrivelse, String leverandør,
			String ernæringsindhold, String fabrikant, double vægt, LocalDate dato, int foderBeholdning) {
		super();
		this.foderID = foderID;
		this.foderNavn = foderNavn;
		this.pris = pris;
		this.foderType = foderType;
		this.beskrivelse = beskrivelse;
		this.leverandør = leverandør;
		this.ernæringsindhold = ernæringsindhold;
		this.fabrikant = fabrikant;
		this.vægt = vægt;
		this.dato = dato;
		this.foderBeholdning = foderBeholdning;
	}
	
	// For at oprette nye foder til database
	public Foder(String foderNavn, double pris, String foderType, String beskrivelse, String leverandør,
			String ernæringsindhold, String fabrikant, double vægt, LocalDate dato, int foderBeholdning) {
		super();
		this.foderNavn = foderNavn;
		this.pris = pris;
		this.foderType = foderType;
		this.beskrivelse = beskrivelse;
		this.leverandør = leverandør;
		this.ernæringsindhold = ernæringsindhold;
		this.fabrikant = fabrikant;
		this.vægt = vægt;
		this.dato = dato;
		this.foderBeholdning = foderBeholdning;
	}
	
	/**
	 * Returns the foder ID.
	 *
	 * @return the foder ID
	 */
	public int getFoderID() {
	    return this.foderID;
	}

	/**
	 * Sets the foder ID.
	 *
	 * @param foderID the foder ID to set
	 */
	public void setFoderID(int foderID) {
	    this.foderID = foderID;
	}

	/**
	 * Returns the foder navn.
	 *
	 * @return the foder navn
	 */
	public String getFoderNavn() {
	    return foderNavn;
	}

	/**
	 * Sets the foder navn.
	 *
	 * @param foderNavn the foder navn to set
	 */
	public void setFoderNavn(String foderNavn) {
	    this.foderNavn = foderNavn;
	}

	/**
	 * Returns the pris.
	 *
	 * @return the pris
	 */
	public double getPris() {
	    return pris;
	}

	/**
	 * Sets the pris.
	 *
	 * @param pris the pris to set
	 */
	public void setPris(double pris) {
	    this.pris = pris;
	}

	/**
	 * Returns the foder type.
	 *
	 * @return the foder type
	 */
	public String getFoderType() {
	    return foderType;
	}

	/**
	 * Sets the foder type.
	 *
	 * @param foderType the foder type to set
	 */
	public void setFoderType(String foderType) {
	    this.foderType = foderType;
	}

	/**
	 * Returns the beskrivelse.
	 *
	 * @return the beskrivelse
	 */
	public String getBeskrivelse() {
	    return beskrivelse;
	}

	/**
	 * Sets the beskrivelse.
	 *
	 * @param beskrivelse the beskrivelse to set
	 */
	public void setBeskrivelse(String beskrivelse) {
	    this.beskrivelse = beskrivelse;
	}

	/**
	 * Returns the leverandør.
	 *
	 * @return the leverandør
	 */
	public String getLeverandør() {
	    return leverandør;
	}

	/**
	 * Sets the leverandør.
	 *
	 * @param leverandør the leverandør to set
	 */
	public void setLeverandør(String leverandør) {
	    this.leverandør = leverandør;
	}

	/**
	 * Returns the ernæringsindhold.
	 *
	 * @return the ernæringsindhold
	 */
	public String getErnæringsindhold() {
	    return ernæringsindhold;
	}

	/**
	 * Sets the ernæringsindhold.
	 *
	 * @param ernæringsindhold the ernæringsindhold to set
	 */
	public void setErnæringsindhold(String ernæringsindhold) {
	    this.ernæringsindhold = ernæringsindhold;
	}

	/**
	 * Returns the fabrikant.
	 *
	 * @return the fabrikant
	 */
	public String getFabrikant() {
	    return fabrikant;
	}

	/**
	 * Sets the fabrikant.
	 *
	 * @param fabrikant the fabrikant to set
	 */
	public void setFabrikant(String fabrikant) {
	    this.fabrikant = fabrikant;
	}

	/**
	 * Returns the vægt.
	 *
	 * @return the vægt
	 */
	public double getVægt() {
	    return vægt;
	}

	/**
	 * Sets the vægt.
	 *
	 * @param vægt the vægt to set
	 */
	public void setVægt(int vægt) {
	    this.vægt = vægt;
	}

	/**
	 * Returns the dato.
	 *
	 * @return the dato
	 */
	public LocalDate getDato() {
	    return dato;
	}

	/**
	 * Sets the dato.
	 *
	 * @param dato the dato to set
	 */
	public void setDato(LocalDate dato) {
	    this.dato = dato;
	}

	/**
	 * Returns the foder beholdning.
	 *
	 * @return the foder beholdning
	 */
	public int getFoderBeholdning() {
	    return foderBeholdning;
	}

	/**
	 * Sets the foder beholdning.
	 *
	 * @param foderBeholdning the foder beholdning to set
	 */
	public void setFoderBeholdning(int foderBeholdning) {
	    this.foderBeholdning = foderBeholdning;
	}
}