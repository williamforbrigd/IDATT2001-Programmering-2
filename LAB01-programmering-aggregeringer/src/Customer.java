

/**
 * Kunde.java Inneholder kundedata.
 */
class Customer {
	private final String name;
	private final String tlf;

	/**
	 * Konstrukt�r: B�de navn og telefon m� oppgis, de kan ikke v�re verken null
	 * eller tomme strenger.
	 */
	public Customer(String name, String tel) {
		if (name == null || name.trim().equals("")) {
			throw new IllegalArgumentException("Navn m� oppgis.");
		}
		if (tel == null || tel.trim().equals("")) {
			throw new IllegalArgumentException("Tlf m� oppgis.");
		}
		this.name = name.trim();
		this.tlf = tel.trim();
	}

	public String getName() {
		return name;
	}

	public String getTlf() {
		return tlf;
	}

	public String toString() {
		return name + ", tlf " + tlf;
	}
}
