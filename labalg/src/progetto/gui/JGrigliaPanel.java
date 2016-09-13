package progetto.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import progetto.builder.Mappa;
import progetto.mediator.Mediator;
import progetto.strategy.Blocco;
import progetto.template_method.Kenken;
import progetto.utility.Cella;
import progetto.utility.Counter;
import progetto.utility.IPLinkedList;
import progetto.utility.InsiemePosizioni;
import progetto.utility.Posizione;

/**
 * 
 * @author Salvatore
 *
 */

public class JGrigliaPanel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	private GridBagLayout gbl = null;
	private GridBagConstraints gbc = null;
	private Kenken kk = null;
	private LinkedList<JCella> listaCelle = null;
	private Mappa soluzione = null;
	private Mappa mappa = null;
	private Mappa ripristino = null;
	private Mediator mediator = null;
	private InsiemePosizioni insieme = null;

	public JGrigliaPanel(Mediator mediator) {
		this.mediator = mediator;
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();
		setLayout(gbl);
		setBackground(Color.decode("#FF7F50"));
		listaCelle = new LinkedList<JCella>();
	}

	public void creaJGrigliaPanel(Mappa mappa) {
		removeAll();
		revalidate();
		repaint();
		this.mappa = mappa;
		insieme = new IPLinkedList();
		kk = new Kenken(mappa);
		boolean flag = false;
		listaCelle = new LinkedList<JCella>();
		for (Blocco blocco : mappa.getInsiemeBlocchi()) {
			String linea = blocco.getOperatore() + " " + String.valueOf(blocco.getRisultato());
			flag = false;
			LinkedList<Cella> lista = blocco.getListaCelle();
			for (int i = 0; i < lista.size(); i++) {
				JCella jc = new JCella(lista.get(i), mappa.getDimensioneMappa());
				jc.setName(jc.getPosizione().toString());
				mediator.manageEvent(new ActionEvent(jc, Counter.generateID(), null));
				if (!flag) {
					flag = true;
					jc.assegnaOR(linea);
				}
				listaCelle.add(jc);
				jc.bordi(lista);
				gbc.gridy = jc.getPosizione().getRiga();
				gbc.gridx = jc.getPosizione().getColonna();
				gbl.setConstraints(jc, gbc);
				add(jc);
			}
		}
	}

	public LinkedList<JCella> getListaJCelle() {
		return listaCelle;
	}

	public boolean possoScriverlo(String val) {
		if (val.equals("1") || val.equals("2") || val.equals("3") || val.equals("4") || val.equals("5")
				|| val.equals("6") || val.equals("7") || val.equals("8") || val.equals("9")) {
			return Integer.parseInt(val) <= mappa.getDimensioneMappa();
		}
		return false;
	}

	public void settaBordo(String file) {
		setBorder(BorderFactory.createTitledBorder(file));
	}

	public void scriviValoreMappa(int numero, Posizione posizione) {
		mappa.write(numero, posizione);
	}

	public void eliminaValoreMappa(Posizione posizione) {
		for (JCella jc : listaCelle) {
			if (jc.getPosizione().equals(posizione))
				jc.setBackground(Color.WHITE);
		}
		mappa.delete(0, posizione);
	}

	public void visualizzaSoluzione() {
		for (JCella jc : listaCelle) {
			jc.setBackground(Color.WHITE);
			jc.setText(String.valueOf(soluzione.getValore(jc.getPosizione())));
		}
	}

	public void restart() {
		for (JCella jc : listaCelle) {
			eliminaValoreMappa(jc.getPosizione());
			jc.setText(" ");
		}
	}

	public Posizione posRandom() {
		Random random = new Random();
		Posizione posizione = null;
		int x = -1;
		int y = -1;
		int dimMappa = mappa.getDimensioneMappa();
		do {
			x = random.nextInt(dimMappa);
			y = random.nextInt(dimMappa);
			posizione = new Posizione(x, y);
		} while (insieme.contiene(posizione));
		insieme.addLast(posizione);
		return posizione;
	}

	public boolean soluzioneCompleta() {
		return soluzione.equals(mappa);
	}

	public void checkSoluzione() {
		if (soluzione != null) {
			for (JCella jc : listaCelle) {
				if (mappa.getValore(jc.getPosizione()).equals(soluzione.getValore(jc.getPosizione()))) {
					jc.setBackground(Color.decode("#4CAF50"));
				} else if (mappa.getValore(jc.getPosizione()) != 0) {
					jc.setBackground(Color.decode("#F44336"));
				} else {
					jc.setBackground(Color.WHITE);
				}
			}
		}
	}

	public void salvaStato() {
		ripristino = new Mappa(mappa);
	}

	public void ripristinaStato() {
		restart();
		for (JCella jc : listaCelle) {
			if (ripristino.getValore(jc.getPosizione()) != 0) {
				scriviValoreMappa(ripristino.getValore(jc.getPosizione()), jc.getPosizione());
				jc.setText(String.valueOf((ripristino.getValore(jc.getPosizione()))));
			}
		}
	}

	public void aiutalo(Posizione pos) {
		for (JCella jc : listaCelle) {
			if (jc.getPosizione().equals(pos)) {
				scriviValoreMappa(soluzione.getValore(pos), pos);
				jc.setBackground(Color.WHITE);
				jc.setText(String.valueOf((soluzione.getValore(pos))));
				jc.setEnabled(false);
			}
		}
	}

	public boolean vuota() {
		return mappa.isEmpty();
	}

	@Override
	public void run() {
		kk.risolvi();
		soluzione = mappa.getSoluzione();
	}

}
