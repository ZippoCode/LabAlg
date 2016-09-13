package progetto.utility;

/**
 * 
 * InsiemePosizioni è un oggetto con lo scopo di aggregare un insieme di
 * Posizioni senza duplicati. I metodi che rende disponibili sono quello di
 * aggiunta di una posizione, tramite le coordinate x e y, oppure tramite un
 * oggetto Posizione. L'aggiunta può avvenire in una posizione i, in ultima o
 * prima posizione. Inoltre permette la rimozione del primo, dell'ultimo o di
 * una generica Posizione. O dato un indice fornisce anche la posizione nella
 * lista oppure il primo o ultimo elemento.
 *
 * @author Salvatore Prochilo
 * @version 1.0
 */

public interface InsiemePosizioni extends Iterable<Posizione> {

	public boolean addFirst(Posizione posizione);

	public boolean addLast(Posizione posizione);

	public boolean addFirst(int x, int y);

	public boolean addLast(int x, int y);

	public boolean removeFirst();

	public boolean removeLast();

	public boolean remove(Posizione posizione);

	public Posizione getFirst();

	public Posizione getLast();

	public Posizione getPosizione(int indice);

	public int dimensione();

	public int getIndice(Posizione posizione);

	public boolean contiene(Posizione posizione);

}
