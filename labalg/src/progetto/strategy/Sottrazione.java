package progetto.strategy;

import java.util.LinkedList;

import progetto.utility.Cella;

/**
 * 
 * @author Salvatore
 * @version 1.0.0
 * 
 **/

/*
 * CONCRETE STRATEGY
 * 
 * Questa strategia implementa la sottrazione.
 */
public class Sottrazione implements Tipo {

	@Override
	public int aggiornaRisultato(LinkedList<Cella> lista) {
		if (lista.size() != 2) {
			// La sottrazione non può avvenire se la lista presenta più di due
			// elementi poiché la sottrazione non è un operazione commutativa
			throw new RuntimeException("Dimensione errata.");
		}
		return Math.abs(lista.get(0).getValore() - lista.get(1).getValore());
	}

}
