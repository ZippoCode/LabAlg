package progetto.command;

import java.awt.Color;

import progetto.builder.Mappa;
import progetto.gui.JPanelCella;
import progetto.gui.JPanelMain;

/**
 * Setta il valore di una JCella salvandosi quello che era scritto
 * precedentemente per facilitare i comandi Avanti e Indietro
 * 
 * @author Salvatore
 * @version 1.2
 */

public class WriteCommand extends AbstractCommand {

	private JPanelCella testo = null;
	private JPanelMain jGrigliaPanel = null;
	private Mappa mappa = null;
	private String oldValue = " ";
	private String newValue = " ";

	public WriteCommand(JPanelCella testo, String value, Mappa mappa, JPanelMain jgp) {
		jGrigliaPanel = jgp;
		testo.setBackground(Color.WHITE);
		this.mappa = mappa;
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
		mappa.write(Integer.parseInt(newValue), testo.getPosizione());
		testo.setBackground(Color.WHITE);
		return true;
	}

	@Override
	public boolean undoIt() {
		testo.setText(oldValue);
		if (oldValue.equals("") || oldValue.equals(" ")) {
			jGrigliaPanel.eliminaValoreMappa(testo.getPosizione());
			mappa.delete(0, testo.getPosizione());
		} else {
			mappa.write(Integer.parseInt(newValue), testo.getPosizione());
		}
		return true;
	}
}
