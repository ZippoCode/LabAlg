package progetto.builder;

import java.util.LinkedList;

import progetto.strategy.Blocco;
import progetto.utility.Blocchi;
import progetto.utility.Griglia;
import progetto.utility.Posizione;

/**
 * 
 * @author Salvatore
 * @version 1.0.1
 * 
 *          PRODUCT Questa classe gestisce la griglia e l'insieme dei blocchi.
 */

public class Mappa implements MappaIF {

	private Griglia griglia = null;
	private Mappa soluzione = null;
	private Blocchi insieme = null;
	private int numeriScritti = 0;

	/**
	 * 
	 * Costruttore normale. Data la dimensione e i blocchi che formano la Mappa
	 * la crea.
	 * 
	 * @param dimensione
	 * @param insieme
	 */
	public Mappa(int dimensione, Blocchi insieme) {
		this.griglia = new Griglia(dimensione);
		this.insieme = insieme;
	}

	/**
	 * Costruttore di copia
	 * 
	 * @param mappa
	 */
	public Mappa(Mappa mappa) {
		griglia = new Griglia(mappa.griglia);
		insieme = new Blocchi(mappa.insieme);
	}

	@Override
	public Posizione getPrimaPosizione() {
		return insieme.getFirst();
	}

	@Override
	public Posizione getPosizioneSuccessiva(Posizione posizione) {
		return insieme.getNext(posizione);
	}

	@Override
	public Posizione getUltimaPosizione() {
		return insieme.getLast();
	}

	@Override
	public int getDimensioneMappa() {
		return griglia.getDimensione();
	}

	public boolean sceltaAssegnabile(int numero, Posizione posizione) {
		return griglia.assegnabile(posizione, numero) && insieme.possoAggiungere(posizione, numero);
	}

	@Override
	public void write(Integer scelta, Posizione puntoDiScelta) {
		griglia.scriviNumero(puntoDiScelta, scelta);
		insieme.scriviBlocco(puntoDiScelta, scelta);
		numeriScritti++;
	}

	@Override
	public void delete(Integer scelta, Posizione puntoDiScelta) {
		griglia.cancellaNumero(puntoDiScelta);
		insieme.cancellaBlocco(puntoDiScelta);
		numeriScritti--;
	}

	@Override
	public Integer getValore(Posizione pos) {
		return griglia.getValore(pos);
	}

	@Override
	public boolean isEmpty() {
		return numeriScritti > 0;
	}

	/**
	 * Salva la posizione
	 */
	@Override
	public void salva() {
		soluzione = new Mappa(this);
	}

	/**
	 * Ritorna la LinkedList dell'insieme dei blocchi
	 * 
	 * @return la LinkedList contenente l'insieme di blocchi
	 */
	@Override
	public LinkedList<Blocco> getInsiemeBlocchi() {
		return insieme.getListaBlocchi();
	}

	/**
	 * Ritorna la soluzione
	 * 
	 * @return la soluzione
	 */
	@Override
	public Mappa getSoluzione() {
		return soluzione;
	}

	@Override
	public boolean equals(Object arg0) {
		if (!(arg0 instanceof Mappa)) {
			return false;
		}
		if (this == arg0) {
			return false;
		}
		Mappa mappa = (Mappa) arg0;
		return mappa.griglia.equals(this.griglia);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(griglia.toString());
		sb.append("\n");
		sb.append(insieme.toString());
		return sb.toString();
	}

}
