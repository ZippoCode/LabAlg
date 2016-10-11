package progetto.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import progetto.mediator.Mediator;
import progetto.utility.Counter;

public class JFrameContatti extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel nome = null;
	private JLabel matricola = null;
	private JLabel email = null;
	private JButton ok;
	private String textName = "Studente: Salvatore Prochilo";
	private String textMatricola = "Matricola: 150097";
	private String textEmail = "E-Mail: prochilo.salvatore@gmail.com";
	private JPanel contentPanel;

	public JFrameContatti(Mediator mediator) {
		contentPanel = new JPanel();
		setContentPane(contentPanel);
		setLayout(new FlowLayout());
		setTitle("Contatti");
		impostaLabel(nome, textName);
		impostaLabel(matricola, textMatricola);
		impostaLabel(email, textEmail);
		ok = new JButton("OK");
		ok.setName("okContatti");
		contentPanel.add(ok);
		mediator.manageEvent(new ActionEvent(ok, Counter.generateID(), null));
		pack();
		setResizable(false);
		setSize(new Dimension(400, 200));
		setAlwaysOnTop(true);
		setLocation(500, 200);
		setVisible(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	private void impostaLabel(JLabel label, String testo) {
		label = new JLabel(testo);
		contentPanel.add(label, BorderLayout.CENTER);
	}
}