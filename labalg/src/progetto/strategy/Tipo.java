package progetto.strategy;

import java.util.LinkedList;

import progetto.utility.Cella;

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

	public int aggiornaRisultato(LinkedList<Cella> lista);

}
