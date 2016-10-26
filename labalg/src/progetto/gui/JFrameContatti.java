package progetto.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
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
	private int vStruct = 5;
	private JPanel contentPanel;

	public JFrameContatti(Mediator mediator) {
		contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		ok = new JButton("OK");
		ok.setName("okContatti");
		ok.setAlignmentX(CENTER_ALIGNMENT);
		mediator.manageEvent(new ActionEvent(ok, Counter.generateID(), null));

		impostaLabel(nome, textName);
		impostaLabel(matricola, textMatricola);
		impostaLabel(email, textEmail);
		contentPanel.add(ok);

		add(contentPanel);
		pack();
		setTitle("Contatti");
		setLocationRelativeTo(null);
		setResizable(false);
		setAlwaysOnTop(true);
		setVisible(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	private void impostaLabel(JLabel label, String testo) {
		label = new JLabel(testo);
		label.setFont(new Font("Ariel", Font.BOLD, 18));
		label.setAlignmentX(CENTER_ALIGNMENT);
		contentPanel.add(label);
		contentPanel.add(Box.createVerticalStrut(vStruct));
	}
}