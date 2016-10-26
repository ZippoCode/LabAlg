package progetto.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import progetto.mediator.Mediator;
import progetto.utility.Counter;

public class JFrameComandi extends JFrame {

	private static final long serialVersionUID = 1440918253326450869L;
	private JLabel testo = null;
	private Dimension dimensione = null;
	private JButton ok;
	private String text = "<html>CLICCARE SU GIOCA E SELEZIONARE LA DIMENSIONE DELLA GRIGLIA,"
			+ "<br>PER SCRIVERE UN NUMERO E' SUFFICIENTIE CLICCARE SULLA POSIZIONE"
			+ "<br>E DIGITARE DA TASTIERA IL NUMERO SCELTO.<br> I BOTTONI<br>RISOLVI : Visualizza la soluzione"
			+ "<br>RESET : Elimina i numeri scritti" + "<br> « e »  : Permettono di navigare sui numeri scritti"
			+ "<br>CHECK : Informa se i numeri scritti sono corretto"
			+ "<br>SAVE e RESTORE : Permetto di salvare e ripristinare lo stato"
			+ "<br>AIUTO : Visualizza un numero in una posizione casuale<br></html>";
	private JPanel contentPanel;

	public JFrameComandi(Mediator mediator) {
		contentPanel = new JPanel();
		setContentPane(contentPanel);
		setLayout(new FlowLayout());
		setTitle("Contatti");
		impostaLabel(testo, text);
		ok = new JButton("OK");
		ok.setName("okComandi");
		contentPanel.add(ok, BorderLayout.LINE_END);
		mediator.manageEvent(new ActionEvent(ok, Counter.generateID(), null));
		pack();
		setResizable(false);
		dimensione = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(new Dimension(dimensione.width / 3, dimensione.height / 3));
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setVisible(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	private void impostaLabel(JLabel label, String testo) {
		label = new JLabel(testo);
		label.setFont(new Font("Ariel", Font.ITALIC, 12));
		contentPanel.add(label, BorderLayout.CENTER);
	}
}
