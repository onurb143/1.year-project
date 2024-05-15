package model;

public class FoderLinje {
	private int mængde;
	private Foder f;

	public FoderLinje(Foder f, int mængde) {
		this.f = f;
		this.mængde = mængde;
	}

	/**
	 * Returns the mængde.
	 *
	 * @return the mængde
	 */
	public int getMængde() {
	    return mængde;
	}

	/**
	 * Sets the mængde.
	 *
	 * @param mængde the mængde to set
	 */
	public void setMængde(int mængde) {
	    this.mængde = mængde;
	}

	/**
	 * Returns the foder.
	 *
	 * @return the foder
	 */
	public Foder getFoder() {
	    return f;
	}

	/**
	 * Sets the foder.
	 *
	 * @param foder the foder to set
	 */
	public void setF(Foder foder) {
	    this.f = foder;
	}

	public void tilfoejFoderlinje(FoderLinje nyFoderlinje) {
		// TODO Auto-generated method stub
		
	}
}
