package progetto.builder;

import java.util.Random;

import progetto.strategy.Blocco;
import progetto.utility.Blocchi;
import progetto.utility.Griglia;
import progetto.utility.InsiemeLL;
import progetto.utility.Insieme;
import progetto.utility.Posizione;

/**
 * 
 * @author Salvatore
 * @version 1.1
 * 
 *          PRODUCT Questa classe gestisce la griglia e l'insieme dei blocchi.
 */

public class Mappa implements MappaIF {

	private Griglia griglia = null;
	private Mappa soluzione = null;
	private Mappa ripristino = null;
	private Blocchi insieme = null;
	private Insieme<Posizione> posizioniRandom = null;
	private int dimensione = -1;
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
		griglia = new Griglia(dimensione);
		this.insieme = insieme;
		this.dimensione = dimensione;
		posizioniRandom = new InsiemeLL<Posizione>();
	}

	/**
	 * Costruttore di copia
	 * 
	 * @param mappa
	 */
	public Mappa(Mappa mappa) {
		griglia = new Griglia(mappa.griglia);
		insieme = new Blocchi(mappa.insieme);
		this.dimensione = mappa.dimensione;
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
		if (soluzione != null && soluzione.getValore(puntoDiScelta) == scelta) {
			posizioniRandom.addLast(puntoDiScelta);
		}
		numeriScritti++;
	}

	@Override
	public void delete(Integer scelta, Posizione puntoDiScelta) {
		griglia.cancellaNumero(puntoDiScelta);
		insieme.cancellaBlocco(puntoDiScelta);
		posizioniRandom.remove(puntoDiScelta);
		numeriScritti--;
	}

	/**
	 * Resetta i valori presenti sulla mappa
	 */
	public void resettaMappa() {
		for (int i = 0; i < dimensione; i++) {
			for (int j = 0; j < dimensione; j++) {
				Posizione pos = new Posizione(i, j);
				delete(getValore(pos), pos);
			}
		}
	}

	/**
	 * 
	 */
	public Insieme<Posizione> posizioniCorrette() {
		InsiemeLL<Posizione> posizioniScritte = new InsiemeLL<Posizione>();
		if (soluzione != null) {
			for (int i = 0; i < dimensione; i++) {
				for (int j = 0; j < dimensione; j++) {
					Posizione pos = new Posizione(i, j);
					if (griglia.getValore(pos) != 0 && griglia.getValore(pos) == soluzione.getValore(pos))
						posizioniScritte.addLast(pos);
				}
			}
		}
		return posizioniScritte;
	}

	public Insieme<Posizione> posizioniScorrette() {
		InsiemeLL<Posizione> posizioniScritte = new InsiemeLL<Posizione>();
		if (soluzione != null) {
			for (int i = 0; i < dimensione; i++) {
				for (int j = 0; j < dimensione; j++) {
					Posizione pos = new Posizione(i, j);
					if (griglia.getValore(pos) != 0 && griglia.getValore(pos) != soluzione.getValore(pos))
						posizioniScritte.addLast(pos);
				}
			}
		}
		return posizioniScritte;
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
	 * Salva la soluzione della mappa
	 */
	@Override
	public void salva() {
		soluzione = new Mappa(this);
	}

	/**
	 * Salva lo stato della mappa
	 */
	public void salvaIstanza() {
		ripristino = new Mappa(this);
	}

	/**
	 * Riporta lo stato della mappa all'ultimo salvataggio
	 */
	public Mappa ripristinaIstanza() {
		Mappa ritorno = new Mappa(ripristino);
		resettaMappa();
		for (int i = 0; i < dimensione; i++) {
			for (int j = 0; j < dimensione; j++) {
				Posizione pos = new Posizione(i, j);
				if (ripristino.getValore(pos) != 0)
					write(ripristino.getValore(pos), pos);
			}
		}
		return ritorno;
	}

	@Override
	public Insieme<Blocco> getInsiemeBlocchi() {
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

	/**
	 * Ritorna una posizione casuale appartenente alla mappa
	 * 
	 * @return Una posizione in modo causale
	 */
	public Posizione getPosizioneRandom() {
		Posizione ritorno = null;
		Random random = new Random();
		do {
			ritorno = new Posizione(random.nextInt(dimensione), random.nextInt(dimensione));
		} while (posizioniRandom.contiene(ritorno));
		posizioniRandom.addLast(ritorno);
		return ritorno;
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
