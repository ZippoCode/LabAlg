package progetto.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import progetto.builder.Mappa;
import progetto.mediator.Mediator;
import progetto.strategy.Blocco;
import progetto.utility.Cella;
import progetto.utility.Counter;
import progetto.utility.Insieme;
import progetto.utility.Posizione;

/**
 * 
 * @author Salvatore
 * @version 2.0.0
 */

public class JPanelMain extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton introGame, introInfo, introContatti, introEsci;
	private GridBagLayout gbl = null;
	private GridBagConstraints gbc = null;
	private HashMap<Posizione, JPanelCella> hashMapJCella = null;
	private Mediator mediator = null;

	public JPanelMain(Mediator mediator) {
		this.mediator = mediator;
		creaJPanelIntro();
	}

	public void creaJPanelIntro() {
		removeAll();
		revalidate();
		repaint();
		setBackground(Color.decode("#B3E5FC"));
		settaBottone(introGame, "introGame", mediator);
		settaBottone(introInfo, "introInfo", mediator);
		settaBottone(introContatti, "introContatti", mediator);
		settaBottone(introEsci, "introEsci", mediator);
	}

	private void settaBottone(JButton bottone, String nome, Mediator mediator) {
		String newName = nome.substring(5, nome.length());
		bottone = new JButton(newName);
		bottone.setName(nome);
		bottone.setEnabled(true);
		bottone.setBackground(Color.WHITE);
		bottone.setFont(new Font("Ariel", Font.CENTER_BASELINE, 30));
		add(bottone);
		mediator.manageEvent(new ActionEvent(bottone, Counter.generateID(), null));
	}

	public void creaJPanelGriglia(Insieme<Blocco> listaBlocchi, int dimensione) {
		removeAll();
		revalidate();
		repaint();
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();
		setBackground(Color.decode("#E0F7FA"));
		setLayout(gbl);
		boolean flag = false;
		hashMapJCella = new HashMap<Posizione, JPanelCella>();
		for (Blocco blocco : listaBlocchi) {
			String linea = blocco.getOperatore() + " " + String.valueOf(blocco.getRisultato());
			flag = false;
			Insieme<Cella> lista = blocco.getListaCelle();
			for (int i = 0; i < lista.dimensione(); i++) {
				JPanelCella jc = new JPanelCella(lista.getElemento(i), dimensione);
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

	public Collection<JPanelCella> getListaJCelle() {
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
		TitledBorder bf = BorderFactory.createTitledBorder(file);
		bf.setTitleColor(Color.BLACK);
		setBorder(bf);
	}

	public void eliminaValoreMappa(Posizione posizione) {
		JPanelCella jc = hashMapJCella.get(posizione);
		if (jc.getPosizione().equals(posizione))
			jc.setBackground(Color.WHITE);
	}

	public void visualizzaSoluzione(Mappa soluzione) {
		for (JPanelCella jc : getListaJCelle()) {
			jc.setBackground(Color.WHITE);
			jc.setText(String.valueOf(soluzione.getValore(jc.getPosizione())));
		}
	}

	public void restart() {
		for (JPanelCella jc : getListaJCelle()) {
			jc.setBackground(Color.WHITE);
			jc.setText(" ");
		}
	}

	public void checkSoluzione(Insieme<Posizione> corrette, Insieme<Posizione> scorrette) {
		for (JPanelCella jc : getListaJCelle()) {
			jc.setBackground(Color.WHITE);
		}
		for (Posizione p : corrette) {
			JPanelCella jc = hashMapJCella.get(p);
			jc.setBackground(Color.decode("#4CAF50"));
		}
		for (Posizione p : scorrette) {
			JPanelCella jc = hashMapJCella.get(p);
			jc.setBackground(Color.decode("#F44336"));
		}
	}

	public void ripristinaStato(Mappa mappa) {
		restart();
		for (JPanelCella jc : getListaJCelle()) {
			if (mappa.getValore(jc.getPosizione()) != 0)
				jc.setText(String.valueOf((mappa.getValore(jc.getPosizione()))));
		}
	}

	public void aiutalo(Posizione pos, int valore) {
		JPanelCella jc = hashMapJCella.get(pos);
		jc.setBackground(Color.WHITE);
		jc.setText(String.valueOf(valore));
		jc.setEnabled(false);
	}

}
