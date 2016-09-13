package progetto.utility;

/**
 * 
 * @author Salvatore
 * @version 1.0.1
 */
/*
 * Questa classe contiene il valore di una determinata posizione della griglia
 * del Kenken. Dunque possiede una posizione e un valore
 */
public class Cella {

	private Posizione posizione;
	private int valore;

	public Cella(Posizione posizione, int valore) {
		if (valore < 0) {
			throw new IllegalArgumentException("Il valore deve essere maggiore o uguale a zero");
		}
		this.posizione = posizione;
		this.valore = valore;
	}// costruttore normale

	public Cella(Posizione posizione) {
		this(posizione, 0);
	}// costruttore normale settando il valore a 0

	public Cella() {
		this(new Posizione(), 0);
	}// costruttore di default

	public Cella(int riga, int colonna, int valore) {
		posizione = new Posizione(riga, colonna);
		this.valore = valore;
	}

	public Cella(int riga, int colonna) {
		posizione = new Posizione(riga, colonna);
		valore = 0;
	}

	public Cella(Cella cella) {
		this(cella.posizione, cella.valore);
	}// costruttore di copia

	// metodi GETTER
	public Posizione getPosizione() {
		return posizione;
	}

	public int getValore() {
		return valore;
	}

	// metodi SETTER
	public void setPosizione(Posizione posizione) {
		this.posizione = posizione;
	}

	public void setValore(int valore) {
		this.valore = valore;
	}

	@Override
	public int hashCode() {
		int hashcode = 1;
		int prime = 31;
		hashcode = prime * hashcode + posizione.hashCode();
		hashcode = prime * hashcode + valore;
		return hashcode;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Cella))
			return false;
		if (obj == this)
			return true;
		Cella c = (Cella) obj;
		if (!(posizione.equals(c.posizione)))
			return false;
		if (valore != c.valore)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return posizione.toString() + " : " + valore;
	}

}
