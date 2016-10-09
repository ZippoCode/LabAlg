package progetto.strategy;

import progetto.utility.Cella;
import progetto.utility.Insieme;

/**
 * 
 * @author Salvatore
 * @version 1.0.0
 */

/*
 * STRATEGY
 * 
 * Rappresenta l'interfaccia di tutte le possibili strategie che è possibile
 * implementare
 */
public interface Tipo {

	public int aggiornaRisultato(Insieme<Cella> lista);

}
