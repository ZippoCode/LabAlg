package progetto.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import progetto.mediator.Mediator;
import progetto.utility.Counter;

/**
 * JRisoltoFrame questo è un frame che viene visualizzato quando si completa il
 * gioco. Permette di iniziare una nuova partita
 * 
 * @author Salvatore
 * @version 1.0.0
 */
public class JRisoltoFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private Label risolto = null;
	private JButton newPlay, noNewPlay = null;

	public JRisoltoFrame(Mediator mediator) {
		JPanel contentPanel = new JPanel();
		setContentPane(contentPanel);
		setLayout(new FlowLayout());
		risolto = new Label("Complimenti hai risolto il gioco, vuoi iniziare una nuova partita?");
		newPlay = new JButton("SI");
		newPlay.setName("play");
		mediator.manageEvent(new ActionEvent(newPlay, Counter.generateID(), null));
		noNewPlay = new JButton("NO");
		noNewPlay.setName("noplay");
		mediator.manageEvent(new ActionEvent(noNewPlay, Counter.generateID(), null));
		contentPanel.add(risolto);
		contentPanel.add(newPlay);
		contentPanel.add(noNewPlay);
		pack();
		setSize(new Dimension(400, 100));
		setLocation(500, 200);
		setVisible(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}