package progetto.utility;

/**
 * 
 * Insieme rappresenta un aggregato di elementi che contiene elementi tutti
 * dello stesso tipo. E' possibile aggiungere un elemento in prima e ultima
 * posizione e rimuovere il primo, ultimo e un elemento qualsiasi. 
 *
 * @author Salvatore Prochilo
 * @version 2.0
 */

public interface Insieme<T> extends Iterable<T> {

	public boolean addFirst(T elemento);

	public boolean addLast(T elemento);

	public boolean removeFirst();

	public boolean removeLast();

	public boolean remove(T elemento);

	public T getFirst();

	public T getLast();

	public T getElemento(int indice);

	public int dimensione();

	public int getIndice(T elemento);

	public boolean contiene(T elemento);

}
