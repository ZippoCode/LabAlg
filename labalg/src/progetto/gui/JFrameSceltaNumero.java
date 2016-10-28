package progetto.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
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
	private ButtonGroup bg = null;
	private JButton conferma = null;
	private JButton annulla = null;

	public JFrameSceltaNumero(Mediator mediator) {
		setName("jsceltaframe");
		pannello = new JPanel();
		pannello.setLayout(new FlowLayout());
		bg = new ButtonGroup();
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
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pannello.setBackground(Color.WHITE);
		setTitle("Scegli dimensione");
		setResizable(false);
		setAlwaysOnTop(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(dim.width / 5, dim.height / 6);
		setLocationRelativeTo(null);
		setVisible(false);
	}

	private void settaggioJRB(JRadioButton jrb, String nome, Mediator mediator) {
		jrb = new JRadioButton(nome);
		jrb.setName(nome);
		jrb.setBackground(Color.WHITE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		jrb.setFont(new Font("Ariel", Font.BOLD, dim.width / 75));
		bg.add(jrb);
		pannello.add(jrb);
		mediator.manageEvent(new ActionEvent(jrb, Counter.generateID(), null));
	}

	private void settaggioJB(JButton bottone, String nome, Mediator mediator) {
		String maiuscolo = nome.substring(0, 1).toUpperCase() + nome.substring(1, nome.length()).toLowerCase();
		bottone = new JButton(maiuscolo);
		bottone.setName(nome);
		bottone.setBackground(Color.WHITE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		bottone.setFont(new Font("Ariel", Font.BOLD, dim.width / 70));
		pannello.add(bottone, BorderLayout.SOUTH);
		mediator.manageEvent(new ActionEvent(bottone, Counter.generateID(), null));
	}

}