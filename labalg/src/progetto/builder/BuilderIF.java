package progetto.builder;

import progetto.utility.InsiemePosizioni;
import progetto.utility.Operatore;

/**
 *
 * @author Salvatore
 */
public interface BuilderIF {

	public void addBlocco(Operatore operatore, int risultato, InsiemePosizioni listaPosizioni);

	public void addBlocco(String operatore, int risultato, InsiemePosizioni listaPosizioni);

	public void buildGriglia(int dim);

}