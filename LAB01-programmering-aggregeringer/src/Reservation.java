
/**
 * Resevasjon.java
 *
 * Et objekt inneholder data om en reservasjon. Operasjoner for � hente ut data,
 * og for � sjekke om overlapp med annen reservasjon.
 */

class Reservation {
	private final Period fromTime;
	private final Period toTime;
	private final Customer customer;

	/**
	 * Konstrukt�r: fraTid m� v�re f�r tilTid. Ingen av argumentene kan v�re null.
	 */
	public Reservation(Period fraTid, Period tilTid, Customer kunde) {
		if (fraTid == null || tilTid == null) {
			throw new IllegalArgumentException("Fra-tid og/eller til-tid er null");
		}
		if (fraTid.compareTo(tilTid) >= 0) {
			throw new IllegalArgumentException("Fra-tid er lik eller etter til-tid");
		}
		if (kunde == null) {
			throw new IllegalArgumentException("Kunde er null");
		}
		this.fromTime = fraTid;
		this.toTime = tilTid;
		this.customer = kunde;
	}

	public Period getFraTid() {
		return fromTime;
	}

	public Period getTilTid() {
		return toTime;
	}

	/**
	 * Metoden returnerer true dersom tidsintervallet [sjekkFraTid, sjekkTilTid]
	 * overlapper med det tidsintervallet som er i det reservasjonsobjektet vi er
	 * inne i [fraTid, tilTid]. Overlapp er ikke definert hvis sjekkFraTid eller
	 * sjekkTilTid er null. Da kaster metoden NullPointerException.
	 */
	public boolean overlapp(Period sjekkFraTid, Period sjekkTilTid) {
		return (sjekkTilTid.compareTo(fromTime) > 0 && sjekkFraTid.compareTo(toTime) < 0);
	}

	public String toString() {
		return "Kunde: " + customer.getName() + ", tlf: " + customer.getTlf() + ", fra " + fromTime.toString() + ", til "
				+ toTime.toString();
	}

	/**
	 * Metode som pr�ver klassen Reservasjon.
	 */
	public static void main(String[] args) {
		Customer k = new Customer("Anne Hansen", "12345678");
		System.out.println("Totalt antall tester: ");
		Reservation r1 = new Reservation(new Period(200302011000L), new Period(200302011100L), k);
		Reservation r2 = new Reservation(new Period(200301211000L), new Period(200301211030L), k);
		Reservation r3 = new Reservation(new Period(200302011130L), new Period(200302011300L), k);
		Reservation r4 = new Reservation(new Period(200302010900L), new Period(200302011100L), k);
		if (r1.toString().equals("Kunde: Anne Hansen, tlf: 12345678, fra 01-02-2003 kl 1000, til 01-02-2003 kl 1100")
				&& r2.toString()
						.equals("Kunde: Anne Hansen, tlf: 12345678, fra 21-01-2003 kl 1000, til 21-01-2003 kl 1030")
				&& r3.toString()
						.equals("Kunde: Anne Hansen, tlf: 12345678, fra 01-02-2003 kl 1130, til 01-02-2003 kl 1300")
				&& r4.toString()
						.equals("Kunde: Anne Hansen, tlf: 12345678, fra 01-02-2003 kl 0900, til 01-02-2003 kl 1100")) {
			System.out.println("Reservasjon: Test 1 vellykket.");
		}

		if (r1.overlapp(new Period(200302011000L), new Period(200302011100L))
				&& !r1.overlapp(new Period(200302021000L), new Period(200302021100L))
				&& r1.overlapp(new Period(200302011030L), new Period(200302011100L))
				&& r1.overlapp(new Period(200302010930L), new Period(200302011030L))) {
			System.out.println("Reservasjon: Test 2 vellykket.");
		}
		// Flg. setning kaster exception (fra-tid lik til-tid)
		// Reservasjon r5 = new Reservasjon(new Tidspunkt(200302011100L), new
		// Tidspunkt(200302011100L), k);
		// Flg. setning kaster exception (fra-tid > til-tid)
		// Reservasjon r5 = new Reservasjon(new Tidspunkt(200302011130L), new
		// Tidspunkt(200302011100L), k);
	}
}
