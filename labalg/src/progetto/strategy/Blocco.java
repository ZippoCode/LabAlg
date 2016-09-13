package progetto.strategy;

import java.util.Iterator;
import java.util.LinkedList;

import progetto.utility.Cella;
import progetto.utility.IPLinkedList;
import progetto.utility.Operatore;
import progetto.utility.Posizione;

/**
 * 
 * @author Salvatore
 * @versione 1.0.7
 * 
 */

/*
 *
 * NEL PATTERN RAPPRESENTA IL CONTEXT
 * 
 * Info: Gestisce un blocco. Ha il compito di scrivere e rimuovere il numero in
 * cella fornire informazioni sugli elementi scritti e sullo stato del blocco
 * 
 */
public class Blocco {

	// Lista di celle contenute nel blocco
	private LinkedList<Cella> celle = new LinkedList<Cella>();
	// Strategia utilizzata. Rappresenta l'operatore aritmetico secondo il quale
	// vengono processati i numeri contenuti nelle celle
	private Tipo strategia = null;
	// Risultato finale
	private int risultato;
	// Risultato corrente
	private int risultatoCorrente = 0;
	// Rappresenta il numero di celle scritte
	private int numeroCelleScritte = 0;
	// E' l'enumeratore che permette di selezionale la strategia
	private Operatore operatore = null;

	public Blocco(Operatore operatore, int risultato, IPLinkedList posizioni) {
		if (risultato <= 0) {
			throw new IllegalArgumentException(risultato + " deve essere maggione di 0");
		}
		this.risultato = risultato;
		this.operatore = operatore;
		strategia = elaboraStrategia(operatore);
		// creazione della lista di celle data la lista di posizioni
		for (Posizione posizione : posizioni.getListaPosizioni()) {
			celle.add(new Cella(posizione));
		}
	}// costruttore normale

	public Blocco(Blocco blocco) {
		this.risultato = blocco.risultato;
		this.operatore = blocco.operatore;
		this.strategia = blocco.strategia;
		celle = new LinkedList<Cella>();
		for (Cella cella : blocco.celle) {
			celle.add(new Cella(cella));
		}
	}// costruttore di copia

	// metodo privato per elaborare la strategia
	private Tipo elaboraStrategia(Operatore operatore) {
		switch (operatore) {
		case ADDIZIONE:
			return new Addizione();
		case SOTTRAZIONE:
			return new Sottrazione();
		case MOLTIPLICAZIONE:
			return new Moltiplicazione();
		case DIVISIONE:
			return new Divisione();
		default:
			return new Nullo();
		}
	}// elaboraStrategia

	// ritorna la Cella corrispondente ad una determinata posizione
	public Cella getCella(Posizione posizione) {
		for (Cella cella : celle) {
			if (cella.getPosizione().equals(posizione)) {
				return cella;
			}
		}
		return null;
	}

	// ritorna il risultato del blocco
	public int getRisultato() {
		return risultato;
	}

	// ritorna il numero di Celle che compongono il blocco
	public int getSize() {
		return celle.size();
	}

	// ritorna una cella corrispondente ad una riga e una colonna
	public Cella getCella(int riga, int colonna) {
		return getCella(new Posizione(riga, colonna));
	}

	// ritorna la strategia utilizzita
	public Tipo getTipo() {
		return strategia;
	}

	// ritorna l'operatore
	public String getOperatore() {
		return Operatore.getStringaOperatore(operatore);
	}

	// scrive un numero in una cella a cui corrisponde la posizione
	public void scriviNumero(Posizione posizione, int numero) {
		if (numero <= 0) {
			throw new IllegalArgumentException(numero + " non superiore allo zero.");
		}
		Cella cella = getCella(posizione);
		if (cella != null) {
			// cerco la cella con la posizione corrispodente e scrivo il numero
			if (cella.getValore() == 0) {
				// verifica se precedentemente era gia' stato scritto un
				// numero
				numeroCelleScritte++;
			}
			cella.setValore(numero);
			risultatoCorrente = strategia.aggiornaRisultato(celle);
		} else {
			throw new IllegalArgumentException("Posizione non presente nel blocco.");
		}
	}

	// rimuove un numero scritto precedentemente in una posizione
	public void rimuoviNumero(Posizione posizione) {
		Cella cella = getCella(posizione);
		if (cella != null) {
			if (cella.getValore() != 0) {
				// Se non c'era alcun numero scritto e' inutile decrementare
				// il numero di celle scritte
				numeroCelleScritte--;
			}
			cella.setValore(0);
			risultatoCorrente = strategia.aggiornaRisultato(celle);
		} else {
			throw new IllegalArgumentException("Posizione non presente nel blocco.");
		}

	}

	// ritorna TRUE se una cella del blocco contiene la posizione
	public boolean contienePosizione(Posizione posizione) {
		Cella cella = getCella(posizione);
		return cella != null;
	}

	// ritorna il valore contenuto nella cella corrispondente alla posizione
	public int getValoreCella(Posizione posizione) {
		Cella cella = getCella(posizione);
		if (cella != null) {
			return cella.getValore();
		} else {
			throw new RuntimeException("Posizione non trovata.");
		}
	}

	// ritorna TRUE se il blocco e' completo
	// per completo si intende che rispettando l'operatore di elementi
	// nella cella otteniamo il risultato
	public boolean bloccoCompleto() {
		return risultato == risultatoCorrente && bloccoSaturo();
	}

	// ritorna TRUE se il numero di elementi scritti e' quale alla dimensione
	// del blocco
	public boolean bloccoSaturo() {
		return numeroCelleScritte == celle.size();
	}

	public boolean quasiSaturo() {
		return numeroCelleScritte == celle.size() - 1;
	}

	// ritorna TRUE se il numero scritto nella posizione completa il blocco
	public boolean completaBlocco(Posizione posizione, int numero) {
		scriviNumero(posizione, numero);
		boolean flag = bloccoCompleto();
		rimuoviNumero(posizione);
		return flag;
	}

	// ritorna la lista delle Celle
	public LinkedList<Cella> getListaCelle() {
		return celle;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Operatore: ").append(strategia.getClass().getSimpleName());
		sb.append('\n');
		sb.append("Risultato: ").append(risultato).append('\n');
		sb.append("Celle: ");
		Iterator<Cella> it = celle.iterator();
		while (it.hasNext()) {
			Cella c = it.next();
			sb.append(c);
			if (it.hasNext()) {
				sb.append(" - ");
			}
		}
		return sb.toString();
	}
}
