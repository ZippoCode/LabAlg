package progetto.utility;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 
 * Implementa InsiemePosizione tramite una LinkedList<Posizione>
 * 
 * @author Salvatore
 * @versione 1.0.1
 */

public class IPLinkedList implements InsiemePosizioni {

	private LinkedList<Posizione> posizioni = null;

	public IPLinkedList() {
		posizioni = new LinkedList<Posizione>();
	}// Costruttore

	@Override
	public boolean addFirst(Posizione posizione) {
		if (!posizioni.contains(posizione)) {
			posizioni.addFirst(posizione);
			return true;
		}
		return false;
	}

	@Override
	public boolean addLast(Posizione posizione) {
		if (!posizioni.contains(posizione)) {
			posizioni.addLast(posizione);
			return true;
		}
		return false;
	}

	@Override
	public boolean addFirst(int x, int y) {
		return addFirst(new Posizione(x, y));
	}

	@Override
	public boolean addLast(int x, int y) {
		return addLast(new Posizione(x, y));
	}

	@Override
	public boolean removeFirst() {
		Posizione ritorno = posizioni.removeFirst();
		return ritorno != null;
	}

	@Override
	public boolean removeLast() {
		Posizione ritorno = posizioni.removeLast();
		return ritorno != null;
	}

	public boolean remove(Posizione posizione) {
		int i = posizioni.indexOf(posizione);
		if (i != -1) {
			posizioni.remove(i);
			return true;
		}
		return false;
	}

	@Override
	public Posizione getFirst() {
		return posizioni.getFirst();
	}

	@Override
	public Posizione getLast() {
		return posizioni.getLast();
	}

	@Override
	public Posizione getPosizione(int indice) {
		return posizioni.get(indice);
	}

	@Override
	public boolean contiene(Posizione posizione) {
		return posizioni.contains(posizione);
	}

	@Override
	public int dimensione() {
		return posizioni.size();
	}

	@Override
	public int getIndice(Posizione posizione) {
		for (int i = 0; i < posizioni.size(); i++) {
			if (posizioni.get(i).equals(posizione))
				return i;
		}
		return -1;
	}

	@Override
	public Iterator<Posizione> iterator() {
		return posizioni.iterator();
	}

	// ritorna la lista delle posizioni
	public LinkedList<Posizione> getListaPosizioni() {
		return posizioni;
	}

}
