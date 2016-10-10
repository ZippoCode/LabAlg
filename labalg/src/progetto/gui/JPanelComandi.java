package progetto.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import progetto.mediator.Mediator;
import progetto.utility.Counter;

/**
 * JComandiPanel è il pannello che consente all'utente di interaggire con la
 * griglia. Sono presenti vari bottoni che permettono varie interazioni quali: -
 * visualizzare la soluzione - resettare la griglia (cancella tutti gli
 * elementi) - avanti e indietro (consentono di navigare tra i numeri scritti) -
 * permettere di controllare se i numeri inseriti sono corretti - salvare
 * l'istanza corrente - ripristinare l'ultima istanza salvata - aiutare il
 * giocatore mostrando lui un numero random
 * 
 * @author Salvatore
 * @version 1.0.2
 *
 */
public class JPanelComandi extends JPanel {

	private static final long serialVersionUID = 1978394422360866472L;
	private JButton risolvi = null;
	private JButton reset = null;
	private JButton avanti = null;
	private JButton indietro = null;
	private JButton check = null;
	private JButton saveState = null;
	private JButton restoreState = null;
	private JButton help = null;

	public JPanelComandi(Mediator mediator) {
		setBackground(Color.decode("#01579B"));
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		sistemaBottone(risolvi, "risolvi", "Risolvi", mediator);
		sistemaBottone(reset, "reset", "Resetta", mediator);
		sistemaBottone(avanti, "avanti", ">>", mediator);
		sistemaBottone(indietro, "indietro", "<<", mediator);
		sistemaBottone(check, "check", "Controlla", mediator);
		sistemaBottone(saveState, "saveState", "Save", mediator);
		sistemaBottone(restoreState, "restoreState", "Restore", mediator);
		sistemaBottone(help, "help", "Aiuto", mediator);
	}

	/**
	 * Poiché le azioni si ripeteno per tutti i bottoni è stato creato questo
	 * metodo per non dover ripetere le operazioni
	 * 
	 * @param bottone
	 * @param name
	 * @param testo
	 * @param mediator
	 */

	public void sistemaBottone(JButton bottone, String name, String testo, Mediator mediator) {
		bottone = new JButton(testo);
		bottone.setName(name);
		mediator.manageEvent(new ActionEvent(bottone, Counter.generateID(), null));
		add(bottone);
	}
}
