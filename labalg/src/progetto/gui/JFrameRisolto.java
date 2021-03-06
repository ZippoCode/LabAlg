package progetto.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import progetto.mediator.Mediator;
import progetto.utility.Counter;

/**
 * JRisoltoFrame questo � un frame che viene visualizzato quando si completa il
 * gioco. Permette di iniziare una nuova partita
 * 
 * @author Salvatore
 * @version 1.0.0
 */
public class JFrameRisolto extends JFrame {

	private static final long serialVersionUID = 1L;
	private Label risolto = null;
	private JButton newPlay, newPlayRandom, noNewPlay = null;

	public JFrameRisolto(Mediator mediator) {
		JPanel contentPanel = new JPanel();
		setContentPane(contentPanel);
		setLayout(new FlowLayout());
		setTitle("Risolto");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		risolto = new Label("Complimenti hai risolto il gioco, vuoi iniziare una nuova partita?");
		newPlay = new JButton("Scegli Griglia");
		newPlay.setName("play");
		mediator.manageEvent(new ActionEvent(newPlay, Counter.generateID(), null));

		newPlayRandom = new JButton("Sceglia Griglia Casuale");
		newPlayRandom.setName("playRandom");
		mediator.manageEvent(new ActionEvent(newPlayRandom, Counter.generateID(), null));

		noNewPlay = new JButton("NO");
		noNewPlay.setName("noplay");
		mediator.manageEvent(new ActionEvent(noNewPlay, Counter.generateID(), null));

		contentPanel.add(risolto);
		contentPanel.add(newPlay);
		contentPanel.add(newPlayRandom);
		contentPanel.add(noNewPlay);
		contentPanel.setBackground(Color.WHITE);
		pack();
		setResizable(false);
		setSize(new Dimension(dim.width / 3, dim.height / 5));
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setVisible(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}