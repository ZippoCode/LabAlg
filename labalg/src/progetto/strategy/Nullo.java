package progetto.strategy;

import java.util.LinkedList;

import progetto.utility.Cella;

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
	public int aggiornaRisultato(LinkedList<Cella> lista) {
		if (lista.size() != 1) {
			// La lista deve contenete al massimo un elemento
			throw new RuntimeException();
		}
		return lista.get(0).getValore();
	}

}
