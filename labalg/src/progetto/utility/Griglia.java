package progetto.utility;

/**
 * 
 * @author Salvatore
 * @version 1.0.9
 */
public class Griglia {

	private int[][] griglia = null;
	private Griglia soluzione = null;
	private int dimensione = 0;

	/**
	 * Costruttore normale. Fornita una dimensione costruisce una griglia
	 * 
	 * @param dimensione
	 */
	public Griglia(int dimensione) {
		if (dimensione < 3 || dimensione > 9) {
			throw new IllegalArgumentException("Dimensione errata.");
		}
		this.dimensione = dimensione;
		griglia = new int[dimensione][dimensione];
	}

	/**
	 * Costruttore di copia
	 * 
	 * @param griglia
	 */
	public Griglia(Griglia griglia) {
		this.dimensione = griglia.dimensione;
		this.griglia = new int[dimensione][dimensione];
		for (int i = 0; i < griglia.dimensione; i++) {
			for (int j = 0; j < griglia.dimensione; j++) {
				this.griglia[i][j] = griglia.griglia[i][j];
			}
		}
	}

	/**
	 * Ritorna true un numero è assegnabile in una posizione
	 * 
	 * @param posizione
	 * @param numero
	 * @return boolean
	 */
	public boolean assegnabile(Posizione posizione, int numero) {
		if (posizione.getRiga() >= griglia.length || posizione.getColonna() >= griglia.length) {
			throw new RuntimeException("Posizione " + posizione + " non presente sulla griglia");
		}
		//CONTROLLO RIGA
		for (int i = 0; i < posizione.getColonna(); i++)
			if (griglia[posizione.getRiga()][i] == numero)
				return false;
		for (int i = griglia.length - 1; i > posizione.getColonna(); i--)
			if (griglia[posizione.getRiga()][i] == numero)
				return false;
		// CONTROLLO COLONNA
		for (int i = 0; i < posizione.getRiga(); i++)
			if (griglia[i][posizione.getColonna()] == numero)
				return false;
		for (int i = griglia.length - 1; i > posizione.getRiga(); i--)
			if (griglia[i][posizione.getColonna()] == numero)
				return false;
		return true;
	}

	/**
	 * 
	 * @return la dimensione della griglia
	 */
	public int getDimensione() {
		return dimensione;
	}

	/**
	 * Scrive un numero in una posizione
	 * 
	 * @param posizione
	 * @param numero
	 */
	public void scriviNumero(Posizione posizione, int numero) {
		griglia[posizione.getRiga()][posizione.getColonna()] = numero;
	}

	/**
	 * Rimuove un numero scritto in una posizione
	 * 
	 * @param posizione
	 */
	public void cancellaNumero(Posizione posizione) {
		griglia[posizione.getRiga()][posizione.getColonna()] = 0;
	}

	/**
	 * Ritorna il numero scritto in una posizione
	 * 
	 * @param posizione
	 * @return numero
	 */
	public int getValore(Posizione posizione) {
		return griglia[posizione.getRiga()][posizione.getColonna()];
	}

	/**
	 * Setta la soluzione come la griglia attuale
	 */
	public void salvaSoluzione() {
		soluzione = new Griglia(this);
	}

	/**
	 * ritorna la soluzione
	 * 
	 * @return Griglia
	 */
	public Griglia getSoluzione() {
		return soluzione;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Griglia)) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		Griglia m = (Griglia) obj;
		if (m.dimensione != this.dimensione)
			return false;
		for (int i = 0; i < griglia.length; i++)
			for (int j = 0; j < griglia.length; j++)
				if (griglia[i][j] != m.griglia[i][j])
					return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\t VISUALIZZAZIONE GRIGLIA:  \n");
		for (int i = 0; i < griglia.length; i++) {
			for (int j = 0; j < griglia.length; j++) {
				sb.append("<" + i + "," + j + "> : ");
				sb.append(griglia[i][j]);
				if (j < griglia.length - 1)
					sb.append("; ");
			}
			sb.append('\n');
		}
		return sb.toString();
	}
}
