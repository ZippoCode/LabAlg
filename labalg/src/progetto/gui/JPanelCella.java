package progetto.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	private int dimensione = 0;
	private boolean modificabile = true;
	public int fattoreScala = -1;

	public JPanelCella(Cella cella, int dimensione, Dimension dimension) {
		this.dimensione = dimensione;
		posizione = cella.getPosizione();
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		if (dimensione >= 6)
			fattoreScala = 18 / dimensione;
		else
			fattoreScala = 10 / dimensione;
		setPreferredSize(new Dimension(dimension.width / dimensione, dimension.height / dimensione));
		fontCella = new Font("Ariel", Font.ITALIC,
				(dimension.width + dimension.height) / (dimensione * fattoreScala * 2));
		fontOR = new Font("Ariel", Font.CENTER_BASELINE, (dimension.width + dimension.height) / (dimensione * fattoreScala * 5));
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
		int top = fattoreScala + 1;
		int left = fattoreScala + 1;
		int bottom = fattoreScala + 1;
		int right = fattoreScala + 1;
		if (posizione.getRiga() == 0) {
			top = fattoreScala * 2;
		} else if (posizione.getRiga() == dimensione - 1) {
			bottom = fattoreScala * 2;
		}
		if (posizione.getColonna() == 0) {
			left = fattoreScala * 2;
		} else if (posizione.getColonna() == dimensione - 1) {
			right = fattoreScala * 2;
		}
		Iterator<Cella> it = lista.iterator();
		while (it.hasNext()) {
			Posizione p = it.next().getPosizione();
			if (!p.equals(posizione)) {
				if (p.getRiga() > posizione.getRiga() && p.getColonna() == posizione.getColonna()) {
					bottom = fattoreScala / 2;
				}
				if (p.getColonna() > posizione.getColonna() && p.getRiga() == posizione.getRiga()) {
					right = fattoreScala / 2;
				}
				if (p.getRiga() < posizione.getRiga() && p.getColonna() == posizione.getColonna()) {
					top = fattoreScala / 2;
				}
				if (p.getColonna() < posizione.getColonna() && p.getRiga() == posizione.getRiga()) {
					left = fattoreScala / 2;
				}
			}
		}
		setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));
	}

	public void assegnaOR(String operatore) {
		label.setText(" " + operatore);
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
