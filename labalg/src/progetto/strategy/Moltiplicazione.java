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
 * Questa strategia implementa la moltiplicazione.
 */
public class Moltiplicazione implements Tipo {

	@Override
	public int aggiornaRisultato(Insieme<Cella> lista) {
		int risultatoCorrente = 1;
		for (Cella cella : lista)
			risultatoCorrente *= cella.getValore();
		return risultatoCorrente;
	}

}
