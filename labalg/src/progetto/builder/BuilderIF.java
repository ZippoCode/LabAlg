package progetto.builder;

import progetto.utility.Insieme;
import progetto.utility.Operatore;
import progetto.utility.Posizione;

/**
 *
 * @author Salvatore
 * 
 * @version 2.0
 */
public interface BuilderIF {

	public void addBlocco(Operatore operatore, int risultato, Insieme<Posizione> listaPosizioni);

	public void addBlocco(String operatore, int risultato, Insieme<Posizione> listaPosizioni);

	public void buildGriglia(int dim);

}