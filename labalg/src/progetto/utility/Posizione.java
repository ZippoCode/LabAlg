package progetto.utility;

/**
 * @author Salvatore
 * @version 1.0.0
 */

/*
 * Contiene le informazioni riguardanti una posizione sulla griglia Ha due
 * valori privati uno per settare la riga e un altro per settare la colonna
 * della griglia
 */
public class Posizione {

	private int riga;
	private int colonna;

	public Posizione(int riga, int colonna) {
		if (riga < 0 || colonna < 0) {
			throw new IllegalArgumentException(
					"I valori di riga e colonna devono essere maggiori di 0");
		}
		this.riga = riga;
		this.colonna = colonna;
	}// costruttore normale

	public Posizione() {
		riga = 0;
		colonna = 0;
	}// costruttore di default

	public Posizione(Posizione p) {
		riga = p.riga;
		colonna = p.colonna;
	}// costruttore di copia

	// metodi Getter
	public int getRiga() {
		return riga;
	}

	public int getColonna() {
		return colonna;
	}

	// metodi Setter
	public void setRiga(int riga) {
		this.riga = riga;
	}

	public void setColonna(int colonna) {
		this.colonna = colonna;
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int hashcode = 1;
		hashcode = prime * hashcode + riga;
		hashcode = prime * hashcode + colonna;
		return hashcode;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Posizione))
			return false;
		if (obj == this)
			return true;
		Posizione p = (Posizione) obj;
		if (p.riga != this.riga || p.colonna != this.colonna)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + riga + ", " + colonna + ")";
	}

}
