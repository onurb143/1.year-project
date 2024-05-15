package model;

public class Medarbejder {
	private int ID;
	private String medarbejderNavn;
	private String telefonNummer;
	private String email;
	private String cpr;
	
	// Opret medarbejder
	
	
	
	
	public Medarbejder(int ID, String medarbejderNavn, String telefonNummer, String email, String cpr) {
		this.ID = ID;
		this.medarbejderNavn = medarbejderNavn;
		this.telefonNummer = telefonNummer;
		this.email = email;
		this.cpr = cpr;
	}
	
	 /**
     * Sets the medarbejderNavn value.
     * @param medarbejderNavn The medarbejderNavn to be set.
     */
    public void setMedarbejderNavn(String medarbejderNavn) {
        this.medarbejderNavn = medarbejderNavn;
    }
    
    /**
     * Retrieves the medarbejderNavn value.
     * @return The medarbejderNavn value.
     */
    public String getMedarbejderNavn() {
        return medarbejderNavn;
    }
    
    /**
     * Retrieves the telefonNummer value.
     * @return The telefonNummer value.
     */
    public String getTelefonNummer() {
        return telefonNummer;
    }
    
    /**
     * Sets the telefonNummer value.
     * @param telefonNummer The telefonNummer to be set.
     */
    public void setTelefonNummer(String telefonNummer) {
        this.telefonNummer = telefonNummer;
    }
    
    /**
     * Retrieves the email value.
     * @return The email value.
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Sets the email value.
     * @param email The email to be set.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Retrieves the cpr value.
     * @return The cpr value.
     */
    public String getCpr() {
        return cpr;
    }
    
    /**
     * Sets the cpr value.
     * @param cpr The cpr to be set.
     */
    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    /**
     * Retrieves the ID value.
     * @return The ID value.
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the ID value.
     * @param iD The ID to be set.
     */
    public void setID(int iD) {
        ID = iD;
    }
}
	

