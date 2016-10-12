package progetto.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import progetto.mediator.Mediator;
import progetto.utility.Counter;

/**
 * 
 * @author Salvatore Prochilo
 * @version 1.2
 */
public class JFrameSceltaNumero extends JFrame {
	private static final long serialVersionUID = -6076695279750416625L;
	private JRadioButton tre, quattro, cinque, sei, sette, otto, nove;
	private JPanel pannello = null;
	private ButtonGroup dim = null;
	private JButton conferma = null;
	private JButton annulla = null;

	public JFrameSceltaNumero(Mediator mediator) {
		setName("jsceltaframe");
		pannello = new JPanel();
		pannello.setLayout(new FlowLayout());
		dim = new ButtonGroup();
		settaggioJRB(tre, "3", mediator);
		settaggioJRB(quattro, "4", mediator);
		settaggioJRB(cinque, "5", mediator);
		settaggioJRB(sei, "6", mediator);
		settaggioJRB(sette, "7", mediator);
		settaggioJRB(otto, "8", mediator);
		settaggioJRB(nove, "9", mediator);
		settaggioJB(conferma, "conferma", mediator);
		settaggioJB(annulla, "annulla", mediator);

		add(pannello);
		pack();
		setTitle("Scegli dimensione");
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setAlwaysOnTop(true);
		setSize(300, 100);
		setLocation(600, 300);
		setVisible(false);
	}

	private void settaggioJRB(JRadioButton jrb, String nome, Mediator mediator) {
		jrb = new JRadioButton(nome);
		jrb.setName(nome);
		dim.add(jrb);
		pannello.add(jrb);
		mediator.manageEvent(new ActionEvent(jrb, Counter.generateID(), null));
	}

	private void settaggioJB(JButton bottone, String nome, Mediator mediator) {
		String maiuscolo = nome.substring(0, 1).toUpperCase() + nome.substring(1, nome.length()).toLowerCase();
		bottone = new JButton(maiuscolo);
		bottone.setName(nome);
		pannello.add(bottone);
		mediator.manageEvent(new ActionEvent(bottone, Counter.generateID(), null));
	}

}