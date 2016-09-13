package progetto.command;

import progetto.gui.JCella;
import progetto.gui.JGrigliaPanel;

/**
 * Setta il valore di una JCella salvandosi quello che era scritto
 * precedentemente per facilitare i comandi Avanti e Indietro
 * 
 * @author Salvatore
 * @version 1.0.2
 */

public class WriteCommand extends AbstractCommand {

	private JCella testo = null;
	private JGrigliaPanel jGrigliaPanel = null;
	private String oldValue = " ";
	private String newValue = " ";

	public WriteCommand(JCella testo, String value, JGrigliaPanel jgp) {
		jGrigliaPanel = jgp;
		oldValue = testo.getText();
		if (oldValue.equals(value))
			return;
		newValue = value;
		this.testo = testo;
		manager.invokeCommand(this);
	}

	@Override
	public boolean doIt() {
		testo.setText(newValue);
		jGrigliaPanel.scriviValoreMappa(Integer.parseInt(newValue), testo.getPosizione());
		return true;
	}

	@Override
	public boolean undoIt() {
		testo.setText(oldValue);
		if (oldValue.equals("") || oldValue.equals(" ")) {
			jGrigliaPanel.eliminaValoreMappa(testo.getPosizione());
		} else
			jGrigliaPanel.scriviValoreMappa(Integer.parseInt(oldValue), testo.getPosizione());
		return true;
	}
}
