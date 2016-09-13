package progetto.template_method;

import progetto.builder.Mappa;
import progetto.utility.IPLinkedList;
import progetto.utility.InsiemePosizioni;
import progetto.utility.Posizione;

/**
 * Estende Problema<P,S> implementando tutti i metodi
 * 
 * @author Salvatore
 * @versione 1.0.3
 */

public class Kenken extends Problema<Posizione, Integer> {

	private Mappa mappa = null;
	private InsiemePosizioni posizioniCompletate = new IPLinkedList();

	public Kenken(Mappa mappa) {
		this.mappa = mappa;
	}

	@Override
	protected Posizione primoPuntoDiScelta() {
		return mappa.getPrimaPosizione();
	}

	@Override
	protected Posizione prossimoPuntoDiScelta(Posizione ps, Integer s) {
		return mappa.getPosizioneSuccessiva(ps);
	}

	@Override
	protected Posizione ultimoPuntoDiScelta() {
		return mappa.getUltimaPosizione();
	}

	@Override
	protected Integer primaScelta(Posizione ps) {
		return 1;
	}

	@Override
	protected Integer prossimaScelta(Integer s) {
		return s + 1;
	}

	@Override
	protected Integer ultimaScelta(Posizione ps) {
		return mappa.getDimensioneMappa();
	}

	@Override
	protected boolean assegnabile(Integer scelta, Posizione puntoDiScelta) {
		return mappa.sceltaAssegnabile(scelta, puntoDiScelta);
	}

	@Override
	protected void assegna(Integer scelta, Posizione puntoDiScelta) {
		mappa.write(scelta, puntoDiScelta);
		posizioniCompletate.addLast(puntoDiScelta);
	}

	@Override
	protected void deassegna(Integer scelta, Posizione puntoDiScelta) {
		mappa.delete(scelta, puntoDiScelta);
		posizioniCompletate.removeLast();
	}

	@Override
	protected Posizione precedentePuntoDiScelta(Posizione puntoDiScelta) {
		return posizioniCompletate.getLast();
	}

	@Override
	protected Integer ultimaSceltaAssegnata(Posizione puntoDiScelta) {
		return mappa.getValore(posizioniCompletate.getLast());
	}

	@Override
	protected void scriviSoluzione(int nrsol) {
		mappa.salva();
	}

}