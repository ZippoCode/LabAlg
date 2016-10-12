package progetto.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import progetto.mediator.Mediator;
import progetto.utility.Counter;
import progetto.utility.GetStringa;

public class JFrameSceltaGriglia extends JFrame {

	private static final long serialVersionUID = 4453130930389664474L;
	private Mediator mediator;

	public JFrameSceltaGriglia(Mediator mediator) {
		JPanel pannello = new JPanel();
		this.mediator = mediator;
		pannello.setLayout(new GridLayout(1, 1));
		JTabbedPane tabella = new JTabbedPane();
		tabella.add("3", creaPannello(GetStringa.numTre, 3));
		tabella.setMnemonicAt(0, KeyEvent.VK_3);
		tabella.add("4", creaPannello(GetStringa.numQuattro, 4));
		tabella.setMnemonicAt(1, KeyEvent.VK_4);
		tabella.add("5", creaPannello(GetStringa.numCinque, 5));
		tabella.setMnemonicAt(2, KeyEvent.VK_5);
		tabella.add("6", creaPannello(GetStringa.numSei, 6));
		tabella.setMnemonicAt(3, KeyEvent.VK_6);
		tabella.add("7", creaPannello(GetStringa.numSette, 7));
		tabella.setMnemonicAt(4, KeyEvent.VK_7);
		tabella.add("8", creaPannello(GetStringa.numOtto, 8));
		tabella.setMnemonicAt(5, KeyEvent.VK_8);
		tabella.add("9", creaPannello(GetStringa.numNove, 9));
		tabella.setMnemonicAt(6, KeyEvent.VK_9);
		pannello.add(tabella);
		tabella.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		add(pannello);
		pack();
		setVisible(false);
		setLocation(600, 50);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(new Dimension(400, 400));
	}

	private JScrollPane creaPannello(int numero, int dimensione) {
		JPanel pannello = new JPanel(new GridLayout(numero, 2, 10, 10));
		JButton[] v = new JButton[numero];
		for (int i = 0; i < v.length; i++) {
			try {
				Image background = ImageIO.read(new File("C:/Users/proch/git/Local_labalg/labalg/src/icon/"
						+ GetStringa.getStringaIcona(dimensione, i + 1) + ".jpg"));
				Image resizeBackground = background.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				ImageIcon icona = new ImageIcon(resizeBackground);
				pannello.add(new JLabel(icona));
				v[i] = new JButton("Seleziona");
				v[i].setBackground(Color.WHITE);
				v[i].setName(GetStringa.getStringa(dimensione, i + 1));
				mediator.manageEvent(new ActionEvent(v[i], Counter.generateID(), null));
				pannello.add(v[i]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		JScrollPane jsp = new JScrollPane(pannello);
		jsp.getVerticalScrollBar().setUnitIncrement(16);
		return jsp;
	}

}
