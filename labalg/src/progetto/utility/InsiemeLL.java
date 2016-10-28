package progetto.utility;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 
 * Implementa Insieme<T> tramite una LinkedList<T>
 * 
 * @author Salvatore
 * @versione 2.0
 */

public class InsiemeLL<T> implements Insieme<T> {

	private LinkedList<T> lista = null;

	public InsiemeLL() {
		lista = new LinkedList<T>();
	}// Costruttore

	@Override
	public boolean addFirst(T elemento) {
		if (!lista.contains(elemento)) {
			lista.addFirst(elemento);
			return true;
		}
		return false;
	}

	@Override
	public boolean addLast(T elemento) {
		if (!lista.contains(elemento)) {
			lista.addLast(elemento);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeFirst() {
		T ritorno = lista.removeFirst();
		return ritorno != null;
	}

	@Override
	public boolean removeLast() {
		T ritorno = lista.removeLast();
		return ritorno != null;
	}

	public boolean remove(T elemento) {
		int i = lista.indexOf(elemento);
		if (i != -1) {
			lista.remove(i);
			return true;
		}
		return false;
	}

	@Override
	public T getFirst() {
		return lista.getFirst();
	}

	@Override
	public T getLast() {
		return lista.getLast();
	}

	@Override
	public T getElemento(int indice) {
		return lista.get(indice);
	}

	@Override
	public boolean contiene(T elemento) {
		return lista.contains(elemento);
	}

	@Override
	public int dimensione() {
		return lista.size();
	}

	@Override
	public int getIndice(T elemento) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).equals(elemento))
				return i;
		}
		return -1;
	}

	@Override
	public Iterator<T> iterator() {
		return lista.iterator();
	}

	// ritorna la lista delle posizioni
	public LinkedList<T> getListaPosizioni() {
		return lista;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(dimensione());
		sb.append("[");
		Iterator<T> it = lista.iterator();
		while (it.hasNext()) {
			T e = it.next();
			sb.append(e);
			if (it.hasNext()) {
				sb.append(" - ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
