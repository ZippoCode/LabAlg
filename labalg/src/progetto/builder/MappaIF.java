package progetto.builder;

import java.util.LinkedList;

import progetto.iterator.MioIteratore;
import progetto.strategy.Blocco;
import progetto.utility.Posizione;

public interface MappaIF extends MioIteratore {

	/**
	 * @return la dimensione della Mappa
	 */
	public int getDimensioneMappa();

	/**
	 * 
	 * @param Integer
	 *            La scelta
	 * @param Integer
	 *            posizione nella quale bisogna scrivere il numero
	 * @return TRUE se l'assegnazione è possibile, FALSE altrimenti
	 */
	public boolean sceltaAssegnabile(int numero, Posizione posizione);

	/**
	 * Scrive un numero in una determinata posizione
	 * 
	 * @param Integer
	 *            numero che vogliamo scrivere
	 * @param Posizione
	 *            punto di scelta
	 */
	public void write(Integer scelta, Posizione puntoDiScelta);

	/**
	 * Cancella un numero in una determinata posizione
	 * 
	 * @param Integer
	 *            numero che vogliamo scrivere
	 * @param Posizione
	 *            punto di scelta
	 */
	public void delete(Integer scelta, Posizione puntoDiScelta);

	/**
	 * Visualizza il numero presente in un posizione, se non è stato scritto
	 * alcun numero ritorna zero
	 * 
	 * @param La
	 *            Posizione
	 * @return
	 */
	public Integer getValore(Posizione pos);

	/**
	 * 
	 */
	public void salva();

	/**
	 * 
	 * @return
	 */
	public LinkedList<Blocco> getInsiemeBlocchi();

	/**
	 * 
	 * @return
	 */
	public Mappa getSoluzione();

	/**
	 * 
	 * @return
	 */
	public boolean isEmpty();
}
