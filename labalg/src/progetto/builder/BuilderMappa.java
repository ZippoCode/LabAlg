package progetto.builder;

import progetto.strategy.Blocco;
import progetto.utility.Griglia;
import progetto.utility.IPLinkedList;
import progetto.utility.InsiemePosizioni;
import progetto.utility.Operatore;

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
	public void addBlocco(Operatore operatore, int risultato, InsiemePosizioni listaPosizioni) {
		insieme.addBlocco(new Blocco(operatore, risultato, (IPLinkedList) listaPosizioni));
	}

	@Override
	public void addBlocco(String operatore, int risultato, InsiemePosizioni listaPosizioni) {
		Operatore op = Operatore.getOperatore(operatore);
		insieme.addBlocco(new Blocco(op, risultato, (IPLinkedList) listaPosizioni));
	}

	@Override
	public void buildGriglia(int dim) {
		griglia = new Griglia(dim);
	}

	public Mappa getMappa() {
		return new Mappa(griglia.getDimensione(), insieme);
	}
}
