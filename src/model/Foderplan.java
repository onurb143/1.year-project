package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Foderplan {
	private int ID;
	private Hest hest;
	private int hyppighed;
	private LocalDate startDato;
	private LocalDate slutDato;
	private Medarbejder medarbejder;
	private List<FoderLinje> fl = new ArrayList<>();
	
	// konstruktør for at hente en allerede skabt foderplan fra databasen
	public Foderplan(int ID, LocalDate startDato, LocalDate slutDato, int hyppighed) {
		this.ID = ID;
		this.hyppighed = hyppighed;
		this.startDato = startDato;
		this.slutDato = slutDato;
	}

	// konstruktør for at lave en ny foderplan
	public Foderplan(LocalDate startDato, LocalDate slutDato, int hyppighed, Medarbejder medarbejder) {
		this.hyppighed = hyppighed;
		this.startDato = startDato;
		this.slutDato = slutDato;
		this.medarbejder = medarbejder;
	}
	
	/**
     * Retrieves the associated Hest object.
     * @return The associated Hest object.
     */
    public Hest getHest() {
        return hest;
    }

    /**
     * Sets the associated Hest object.
     * @param hest The Hest object to be associated.
     */
    public void tilføjHest(Hest hest) {
        this.hest = hest;
    }

    /**
     * Retrieves the list of FoderLinje objects.
     * @return The list of FoderLinje objects.
     */
    public List<FoderLinje> getFoderLinje() {
        return fl;
    }

    /**
     * Adds a FoderLinje object to the list.
     * @param fl The FoderLinje object to be added.
     */
    public void tilføjFoderLinje(FoderLinje fl) {
        this.fl.add(fl);
    }

    /**
     * Retrieves the associated Medarbejder object.
     * @return The associated Medarbejder object.
     */
    public Medarbejder getMedarbejder() {
        return this.medarbejder;
    }

    /**
     * Sets the associated Medarbejder object.
     * @param m The Medarbejder object to be associated.
     */
    public void setMedarbejder(Medarbejder m) {
        this.medarbejder = m;
    }

    /**
     * Retrieves the hyppighed value.
     * @return The hyppighed value.
     */
    public int getHyppighed() {
        return hyppighed;
    }

    /**
     * Sets the hyppighed value.
     * @param hyppighed The hyppighed value to be set.
     */
    public void setHyppighed(int hyppighed) {
        this.hyppighed = hyppighed;
    }

    /**
     * Retrieves the startDato value.
     * @return The startDato value.
     */
    public LocalDate getStartDato() {
        return startDato;
    }

    /**
     * Sets the startDato value.
     * @param startDato The startDato value to be set.
     */
    public void setStartDato(LocalDate startDato) {
        this.startDato = startDato;
    }

    /**
     * Retrieves the slutDato value.
     * @return The slutDato value.
     */
    public LocalDate getSlutDato() {
        return slutDato;
    }

    /**
     * Sets the slutDato value.
     * @param slutDato The slutDato value to be set.
     */
    public void setSlutDato(LocalDate slutDato) {
        this.slutDato = slutDato;
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
     * @param iD The ID value to be set.
     */
    public void setID(int iD) {
        ID = iD;
    }

	public void setFoderlinje(List<FoderLinje> fl) {
		this.fl = fl;
	}
}
