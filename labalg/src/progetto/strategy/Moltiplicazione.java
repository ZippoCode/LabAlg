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
 * Questa strategia implementa la moltiplicazione.
 */
public class Moltiplicazione implements Tipo {

	@Override
	public int aggiornaRisultato(LinkedList<Cella> lista) {
		int risultatoCorrente = 1;
		for (Cella cella : lista)
			risultatoCorrente *= cella.getValore();
		return risultatoCorrente;
	}

}
