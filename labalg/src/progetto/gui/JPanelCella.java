package progetto.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import progetto.utility.Cella;
import progetto.utility.Insieme;
import progetto.utility.Posizione;

/**
 * JCella estende il Component JButton e ai fini del progetto è la
 * rappresentazione grafica di una Cella della griglia
 * 
 * @author Salvatore
 * @version 1.2
 */
public class JPanelCella extends JButton {

	private static final long serialVersionUID = -8325244841699871850L;
	private Font fontCella = null, fontOR = null;
	private JLabel label = null;
	private Posizione posizione = null;
	private Dimension dimension = null;
	private int dimensione = 0;
	private boolean modificabile = true;

	public JPanelCella(Cella cella, int dimensione) {
		this.dimensione = dimensione;
		posizione = cella.getPosizione();
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(new Dimension((dimension.width-850) / dimensione, (dimension.height-250) / dimensione));
		fontCella = new Font("Ariel", Font.ITALIC, ((dimension.width-dimension.height)-300) / dimensione);
		fontOR = new Font("Ariel", Font.BOLD, (dimension.height-650) / dimensione);
		label = new JLabel(" ");
		label.setFont(fontOR);
		add(label, BorderLayout.BEFORE_FIRST_LINE);

		setName(posizione.toString());
		setForeground(Color.BLACK);
		setFont(fontCella);
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.WHITE));
		setHorizontalAlignment(JTextField.CENTER);
	}

	/**
	 * Setta i bordi di una cella
	 * 
	 * @param lista
	 */
	public void bordi(Insieme<Cella> lista) {
		int fattore = 0;
		if (dimensione >= 6)
			fattore = 18 / dimensione;
		else
			fattore = 10 / dimensione;
		int top = fattore + 1;
		int left = fattore + 1;
		int bottom = fattore + 1;
		int right = fattore + 1;
		if (posizione.getRiga() == 0) {
			top = fattore * 2;
		} else if (posizione.getRiga() == dimensione - 1) {
			bottom = fattore * 2;
		}
		if (posizione.getColonna() == 0) {
			left = fattore * 2;
		} else if (posizione.getColonna() == dimensione - 1) {
			right = fattore * 2;
		}
		Iterator<Cella> it = lista.iterator();
		while (it.hasNext()) {
			Posizione p = it.next().getPosizione();
			if (!p.equals(posizione)) {
				if (p.getRiga() > posizione.getRiga() && p.getColonna() == posizione.getColonna()) {
					bottom = fattore / 2;
				}
				if (p.getColonna() > posizione.getColonna() && p.getRiga() == posizione.getRiga()) {
					right = fattore / 2;
				}
				if (p.getRiga() < posizione.getRiga() && p.getColonna() == posizione.getColonna()) {
					top = fattore / 2;
				}
				if (p.getColonna() < posizione.getColonna() && p.getRiga() == posizione.getRiga()) {
					left = fattore / 2;
				}
			}
		}
		setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));
	}

	public void assegnaOR(String operatore) {
		label.setText(" "+operatore);
	}

	public Posizione getPosizione() {
		return posizione;
	}

	@Override
	public void setText(String stringa) {
		if (modificabile)
			super.setText(stringa);
	}

	@Override
	public void setBackground(Color colore) {
		if (modificabile)
			super.setBackground(colore);
	}

	@Override
	public void setEnabled(boolean b) {
		modificabile = b;
		super.setEnabled(false);
	}
}
