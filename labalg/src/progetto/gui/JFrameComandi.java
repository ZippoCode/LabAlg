package progetto.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import progetto.mediator.Mediator;
import progetto.utility.Counter;

public class JFrameComandi extends JFrame {

	private static final long serialVersionUID = 1440918253326450869L;
	private Dimension dimensione = null;
	private JTextArea txa = null;
	private JButton ok;
	private String text = "CLICCARE SU GIOCA E SELEZIONARE LA DIMENSIONE DELLA GRIGLIAPER SCRIVERE UN NUMERO E' SUFFICIENTIE CLICCARE SULLA POSIZIONE E DIGITARE DA TASTIERA IL NUMERO SCELTO. I BOTTONI "
			+ "\nRISOLVI : Visualizza la soluzione " + "\nRESET : Elimina i numeri scritti "
			+ "\n<< e >>  : Permettono di navigare sui numeri scritti "
			+ "\nCONTROLLA : Informa se i numeri scritti sono corretto "
			+ "\nSAVE e RESTORE : Permetto di salvare e ripristinare lo stato "
			+ "\nAIUTO : Visualizza un numero in una posizione casuale";
	private JPanel panelText, panelButton;

	public JFrameComandi(Mediator mediator) {
		panelText = new JPanel();
		panelText.setLayout(new BorderLayout());
		panelText.setBackground(Color.white);
		panelButton = new JPanel();
		panelButton.setBackground(Color.white);
		dimensione = Toolkit.getDefaultToolkit().getScreenSize();
		setTitle("Comandi");
		txa = new JTextArea();
		txa.setEditable(false);
		txa.setText(text);
		txa.setFont(new Font("Ariel", Font.PLAIN, dimensione.height / 55));
		txa.setLineWrap(true);
		txa.setWrapStyleWord(true);
		ok = new JButton("ESCI");
		ok.setName("okComandi");
		ok.setFont(new Font("Ariel", Font.PLAIN, dimensione.height / 50));
		panelText.add(txa, BorderLayout.CENTER);
		panelButton.add(ok, BorderLayout.CENTER);
		mediator.manageEvent(new ActionEvent(ok, Counter.generateID(), null));
		pack();
		setResizable(false);
		setSize(new Dimension(dimensione.width / 3, dimensione.height / 3));
		add(panelText, BorderLayout.CENTER);
		add(panelButton, BorderLayout.SOUTH);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setVisible(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}
