package progetto.strategy;

import progetto.utility.Cella;
import progetto.utility.Insieme;

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
	public int aggiornaRisultato(Insieme<Cella> lista) {
		if (lista.dimensione() != 2) {
			// La sottrazione non pu� avvenire se la lista presenta pi� di due
			// elementi poich� la sottrazione non � un operazione commutativa
			throw new RuntimeException("Dimensione errata.");
		}
		return Math.abs(lista.getElemento(0).getValore() - lista.getElemento(1).getValore());
	}

}
