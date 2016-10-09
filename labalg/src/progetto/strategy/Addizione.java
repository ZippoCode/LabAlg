package progetto.strategy;

import progetto.utility.Cella;
import progetto.utility.Insieme;

/**
 * 
 * @author Salvatore
 * @version 1.0.0
 * 
 **/

public class Addizione implements Tipo {

	@Override
	public int aggiornaRisultato(Insieme<Cella> lista) {
		int risultatoCorrente = 0;
		for (Cella cella : lista) {
			risultatoCorrente += cella.getValore();
		}
		return risultatoCorrente;
	}

}
