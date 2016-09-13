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
 * Questa strategia implementa la divisione.
 */
public class Divisione implements Tipo {

	@Override
	public int aggiornaRisultato(LinkedList<Cella> lista) {
		if (lista.size() != 2)
			// Se il numero di elementi della lista è superiore a due non è
			// possibile effettuare la sottrizione ai fini del KenKen
			throw new RuntimeException("Dimensione errata.");
		int a = lista.get(0).getValore();
		int b = lista.get(1).getValore();
		if (a == 0 && b == 0)
			return 0;
		if (a == 0 && b != 0)
			return b;
		if (b == 0 && a != 0)
			return a;
		if (a >= b) {
			if (a % b != 0)
				return 0;
			else
				return a / b;
		} else {
			if (b % a != 0)
				return 0;
			else
				return b / a;
		}
	}

}
