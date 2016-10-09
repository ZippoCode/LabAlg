package progetto.builder;

import progetto.strategy.Blocco;
import progetto.utility.Blocchi;
import progetto.utility.Griglia;
import progetto.utility.InsiemeLL;
import progetto.utility.Insieme;
import progetto.utility.Operatore;
import progetto.utility.Posizione;

/**
 * 
 * @author Salvatore
 * @version 1.0.1 CONCRETE BUILDER Si occupa della costruzione di oggetto di
 *          tipo Mappa. offre vari modi per la costruzione di un blocco, e
 *          inoltre si di come costruire la griglia
 */
public class BuilderMappa implements BuilderIF {

	private Blocchi insieme = null;
	private Griglia griglia = null;

	public BuilderMappa() {
		insieme = new Blocchi();
	}

	@Override
	public void addBlocco(Operatore operatore, int risultato, Insieme<Posizione> listaPosizioni) {
		insieme.addBlocco(new Blocco(operatore, risultato, (InsiemeLL<Posizione>) listaPosizioni));
	}

	@Override
	public void addBlocco(String operatore, int risultato, Insieme<Posizione> listaPosizioni) {
		Operatore op = Operatore.getOperatore(operatore);
		insieme.addBlocco(new Blocco(op, risultato, (InsiemeLL<Posizione>) listaPosizioni));
	}

	@Override
	public void buildGriglia(int dim) {
		griglia = new Griglia(dim);
	}

	public Mappa getMappa() {
		return new Mappa(griglia.getDimensione(), insieme);
	}
}
