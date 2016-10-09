package progetto.strategy;

import progetto.utility.Cella;
import progetto.utility.Insieme;

public class Nullo implements Tipo {
	/**
	 * 
	 * @author Salvatore
	 * @version 1.0.0
	 * 
	 **/

	/*
	 * CONCRETE STRATEGY
	 * 
	 * Questa strategia implementa l'operazione nulla. Cioè il blocco è composto
	 * da una sola cella e dunque non deve essere eseguita alcuna operazione
	 */
	@Override
	public int aggiornaRisultato(Insieme<Cella> lista) {
		if (lista.dimensione() != 1) {
			// La lista deve contenete al massimo un elemento
			throw new RuntimeException();
		}
		return lista.getElemento(0).getValore();
	}

}
