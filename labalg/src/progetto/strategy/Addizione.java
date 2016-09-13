package progetto.strategy;

import java.util.LinkedList;

import progetto.utility.Cella;

/**
 * 
 * @author Salvatore
 * @version 1.0.0
 * 
 **/

public class Addizione implements Tipo {

	@Override
	public int aggiornaRisultato(LinkedList<Cella> lista) {
		int risultatoCorrente = 0;
		for (Cella cella : lista) {
			risultatoCorrente += cella.getValore();
		}
		return risultatoCorrente;
	}

}
