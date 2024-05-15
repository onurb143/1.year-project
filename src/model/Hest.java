package model;

import java.time.LocalDate;

public class Hest {
	private int id;
	private String navn;
	private int hestCHR;
	private String født;
	private String køn;
	private String farve;
	private String far;
	private String mor;
	private LocalDate sidstVaccineret;
	private LocalDate sidstSkifterSko;

	public Hest(int id, int hestCHR, String navn, String født, String køn, String farve, String far, String mor,
			LocalDate sidstVaccineret, LocalDate sidstSkifterSko) {
		this.id = id;
		this.navn = navn;
		this.hestCHR = hestCHR;
		this.født = født;
		this.køn = køn;
		this.farve = farve;
		this.far = far;
		this.mor = mor;
		this.sidstVaccineret = sidstVaccineret;
		this.sidstSkifterSko = sidstSkifterSko;
	}

	 /**
     * Retrieves the navn value.
     * @return The navn value.
     */
    public String getNavn() {
        return navn;
    }

    /**
     * Sets the navn value.
     * @param navn The navn value to be set.
     */
    public void setNavn(String navn) {
        this.navn = navn;
    }

    /**
     * Retrieves the hestCHR value.
     * @return The hestCHR value.
     */
    public int getHestCHR() {
        return hestCHR;
    }

    /**
     * Sets the hestCHR value.
     * @param hestCHR The hestCHR value to be set.
     */
    public void setHestCHR(int hestCHR) {
        this.hestCHR = hestCHR;
    }

    /**
     * Retrieves the født value.
     * @return The født value.
     */
    public String getFødt() {
        return født;
    }

    /**
     * Sets the født value.
     * @param født The født value to be set.
     */
    public void setFødt(String født) {
        this.født = født;
    }

    /**
     * Retrieves the køn value.
     * @return The køn value.
     */
    public String isKøn() {
        return køn;
    }

    /**
     * Sets the køn value.
     * @param køn The køn value to be set.
     */
    public void setKøn(String køn) {
        this.køn = køn;
    }

    /**
     * Retrieves the farve value.
     * @return The farve value.
     */
    public String getFarve() {
        return farve;
    }

    /**
     * Sets the farve value.
     * @param farve The farve value to be set.
     */
    public void setFarve(String farve) {
        this.farve = farve;
    }

    /**
     * Retrieves the far value.
     * @return The far value.
     */
    public String getFar() {
        return far;
    }

    /**
     * Sets the far value.
     * @param far The far value to be set.
     */
    public void setFar(String far) {
        this.far = far;
    }

    /**
     * Retrieves the mor value.
     * @return The mor value.
     */
    public String getMor() {
        return mor;
    }

    /**
     * Sets the mor value.
     * @param mor The mor value to be set.
     */
    public void setMor(String mor) {
        this.mor = mor;
    }

    /**
     * Retrieves the sidstVaccineret value.
     * @return The sidstVaccineret value.
     */
    public LocalDate isSidstVaccineret() {
        return sidstVaccineret;
    }

    /**
     * Sets the sidstVaccineret value.
     * @param sidstVaccineret The sidstVaccineret value to be set.
     */
    /**
     * Sets the sidstSkifterSko value.
     * @param sidstSkifterSko The sidstSkifterSko value to be set.
     */
    public void setSidstSkifterSko(LocalDate sidstSkifterSko) {
        this.sidstSkifterSko = sidstSkifterSko;
    }

    /**
     * Retrieves the id value.
     * @return The id value.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id value.
     * @param id The id value to be set.
     */
    public void setId(int id) {
        this.id = id;
    }
}
