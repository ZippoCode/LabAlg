package progetto.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import progetto.builder.Mappa;
import progetto.mediator.Mediator;
import progetto.strategy.Blocco;
import progetto.utility.Cella;
import progetto.utility.Counter;
import progetto.utility.InsiemePosizioni;
import progetto.utility.Posizione;

/**
 * 
 * @author Salvatore
 * @version 2.0.0
 */

public class JGrigliaPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private GridBagLayout gbl = null;
	private GridBagConstraints gbc = null;
	private HashMap<Posizione, JCella> hashMapJCella = null;
	private Mediator mediator = null;

	public JGrigliaPanel(Mediator mediator) {
		this.mediator = mediator;
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();
		setLayout(gbl);
		setBackground(Color.decode("#FF7F50"));
	}

	public void creaJGrigliaPanel(LinkedList<Blocco> listaBlocchi, int dimensione) {
		removeAll();
		revalidate();
		repaint();
		boolean flag = false;
		hashMapJCella = new HashMap<Posizione, JCella>();
		for (Blocco blocco : listaBlocchi) {
			String linea = blocco.getOperatore() + " " + String.valueOf(blocco.getRisultato());
			flag = false;
			LinkedList<Cella> lista = blocco.getListaCelle();
			for (int i = 0; i < lista.size(); i++) {
				JCella jc = new JCella(lista.get(i), dimensione);
				jc.setName(jc.getPosizione().toString());
				mediator.manageEvent(new ActionEvent(jc, Counter.generateID(), null));
				if (!flag) {
					flag = true;
					jc.assegnaOR(linea);
				}
				hashMapJCella.put(jc.getPosizione(), jc);
				jc.bordi(lista);
				gbc.gridy = jc.getPosizione().getRiga();
				gbc.gridx = jc.getPosizione().getColonna();
				gbl.setConstraints(jc, gbc);
				add(jc);
			}
		}
	}

	public Collection<JCella> getListaJCelle() {
		return hashMapJCella.values();
	}

	public boolean possoScriverlo(String val, int dimensione) {
		if (val.equals("1") || val.equals("2") || val.equals("3") || val.equals("4") || val.equals("5")
				|| val.equals("6") || val.equals("7") || val.equals("8") || val.equals("9")) {
			return Integer.parseInt(val) <= dimensione;
		}
		return false;
	}

	public void settaBordo(String file) {
		setBorder(BorderFactory.createTitledBorder(file));
	}

	public void eliminaValoreMappa(Posizione posizione) {
		JCella jc = hashMapJCella.get(posizione);
		if (jc.getPosizione().equals(posizione))
			jc.setBackground(Color.WHITE);
	}

	public void visualizzaSoluzione(Mappa soluzione) {
		for (JCella jc : getListaJCelle()) {
			jc.setBackground(Color.WHITE);
			jc.setText(String.valueOf(soluzione.getValore(jc.getPosizione())));
		}
	}

	public void restart() {
		for (JCella jc : getListaJCelle()) {
			jc.setBackground(Color.WHITE);
			jc.setText(" ");
		}
	}

	public void checkSoluzione(InsiemePosizioni corrette, InsiemePosizioni scorrette) {
		for (JCella jc : getListaJCelle()) {
			jc.setBackground(Color.WHITE);
		}
		for (Posizione p : corrette) {
			JCella jc = hashMapJCella.get(p);
			jc.setBackground(Color.decode("#4CAF50"));
		}
		for (Posizione p : scorrette) {
			JCella jc = hashMapJCella.get(p);
			jc.setBackground(Color.decode("#F44336"));
		}
	}

	public void ripristinaStato(Mappa mappa) {
		restart();
		for (JCella jc : getListaJCelle()) {
			if (mappa.getValore(jc.getPosizione()) != 0)
				jc.setText(String.valueOf((mappa.getValore(jc.getPosizione()))));
		}
	}

	public void aiutalo(Posizione pos, int valore) {
		JCella jc = hashMapJCella.get(pos);
		jc.setBackground(Color.WHITE);
		jc.setText(String.valueOf(valore));
		jc.setEnabled(false);
	}

}
