package progetto.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import progetto.mediator.Mediator;
import progetto.utility.Counter;

public class JFrameUscita extends JFrame {

	private static final long serialVersionUID = 3681444314437491551L;
	private Label testo = null;
	private JButton siExit, noExit = null;

	public JFrameUscita(Mediator mediator) {
		JPanel contentPanel = new JPanel();
		setContentPane(contentPanel);
		setTitle("Esci");
		setLayout(new FlowLayout());
		testo = new Label("Confermi di voler uscire dall'applicazione?\n PERDERAI TUTTI I PROGRESSI.");
		siExit = new JButton("SI");
		siExit.setName("siExit");
		mediator.manageEvent(new ActionEvent(siExit, Counter.generateID(), null));
		noExit = new JButton("NO");
		noExit.setName("noExit");
		mediator.manageEvent(new ActionEvent(noExit, Counter.generateID(), null));
		contentPanel.add(testo);
		contentPanel.add(siExit);
		contentPanel.add(noExit);
		contentPanel.setBackground(Color.WHITE);
		pack();
		setResizable(false);
		setSize(new Dimension(450, 100));
		setAlwaysOnTop(true);
		setLocation(500, 200);
		setVisible(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	public void messaggioDiUscita() {
		JOptionPane.showMessageDialog(null, "Premi OK per uscire", "Bye", JOptionPane.CLOSED_OPTION);
		System.exit(0);
	}

}
