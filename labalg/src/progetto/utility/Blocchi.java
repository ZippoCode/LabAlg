package progetto.utility;

import java.util.Iterator;
import java.util.LinkedList;

import progetto.strategy.Blocco;

/**
 * @author Salvatore
 * @version 1.0.5
 */

/*
 * Gestisce i blocchi presenti sulla Mappa tramite una LinkedList. Fornisce un
 * deteminato blocco data una posizione oppure i valori di riga e colonna
 * inoltre fornisce la prima posizione, l'ultima e una successiva.
 */

public class Blocchi {

	private LinkedList<Blocco> blocchi = null;
	private InsiemePosizioni posizioni = null;

	/**
	 * Costruttore normale
	 */
	public Blocchi() {
		blocchi = new LinkedList<Blocco>();
		posizioni = new IPLinkedList();
	}// costruttore di default

	/**
	 * Costruttore di copia
	 * 
	 * @param insieme
	 */
	public Blocchi(Blocchi insieme) {
		blocchi = new LinkedList<Blocco>();
		posizioni = new IPLinkedList();
		for (Blocco blocco : insieme.blocchi)
			blocchi.add(new Blocco(blocco));
		for (Posizione posizione : insieme.posizioni)
			posizioni.addLast(new Posizione(posizione));
	}

	// Aggiunge un blocco e le posizioni appartenenti
	public void addBlocco(Blocco blocco) {
		blocchi.addLast(blocco);
		for (Cella cella : blocco.getListaCelle()) {
			posizioni.addLast(cella.getPosizione());
		}
	}

	/**
	 * @param la
	 *            posizione
	 * @return il blocco che contiene la posizione
	 */
	public Blocco getBlocco(Posizione posizione) {
		for (Blocco blocco : blocchi) {
			if (blocco.contienePosizione(posizione)) {
				return blocco;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param posizione
	 * @param numero
	 * @return true se è possibile scrivere un numero in una posizione
	 */
	public boolean possoAggiungere(Posizione posizione, int numero) {
		Blocco blocco = getBlocco(posizione);
		if (blocco != null) {
			if (blocco.quasiSaturo() && !blocco.completaBlocco(posizione, numero))
				return false;
		}
		return true;
	}

	/**
	 * Scrive un numero in una determinata posizione
	 * 
	 * @param posizione
	 *            posizione nella quale si vuole scrivere il numero
	 * @param numero
	 *            numero da scrivere
	 */
	public void scriviBlocco(Posizione posizione, int numero) {
		Blocco blocco = getBlocco(posizione);
		if (blocco != null) {
			blocco.scriviNumero(posizione, numero);
		}
	}

	/**
	 * Rimuove un numero da un blocco
	 * 
	 * @param posizione
	 *            posizioneella quale vogliamo rimuovere in numero
	 */
	public void cancellaBlocco(Posizione posizione) {
		Blocco blocco = getBlocco(posizione);
		if (blocco != null) {
			blocco.rimuoviNumero(posizione);
		}
	}

	/**
	 * Ritorna il blocco contente la posizione indicata come il numero di riga e
	 * colonna
	 * 
	 * @param int
	 *            riga della posizione cercata
	 * @param int
	 *            colonna della posizione cercata
	 * @return Blocco il blocco che ocntiene la posizione cercata
	 */

	public Blocco getBlocco(int riga, int colonna) {
		return getBlocco(new Posizione(riga, colonna));
	}

	// ritorna la lista dei blocchi
	public LinkedList<Blocco> getListaBlocchi() {
		return blocchi;
	}

	// ritorna la lista delle posizioni
	public InsiemePosizioni getListaPosizioni() {
		return posizioni;
	}

	// ritorna la prima posizione
	public Posizione getFirst() {
		return posizioni.getFirst();
	}

	// ritorna l'ultima posizione
	public Posizione getLast() {
		return posizioni.getLast();
	}

	// ritorna la posizione successiva
	public Posizione getNext(Posizione posizione) {
		if (!posizioni.contiene(posizione))
			return null;
		for (int i = 0; i < posizioni.dimensione(); i++) {
			if (i < posizioni.dimensione() - 1 && posizioni.getPosizione(i).equals(posizione))
				return posizioni.getPosizione(i + 1);
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<Blocco> it = blocchi.iterator();
		while (it.hasNext()) {
			Blocco blocco = it.next();
			sb.append(blocco);
			if (it.hasNext()) {
				sb.append('\n');
			}
		}
		return sb.toString();
	}

}
