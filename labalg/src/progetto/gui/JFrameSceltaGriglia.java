package progetto.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(false);
		setSize(new Dimension(400, 400));
	}

	private JPanel creaPannello(int numero, int dimensione) {
		JPanel pannello = new JPanel(new GridLayout(numero, 2));
		JButton[] v = new JButton[numero];
		for (int i = 0; i < v.length; i++) {
			pannello.add(new JLabel(GetStringa.getStringa(dimensione,i)));
			v[i] = new JButton("3x3 " + i);
			v[i].setName(GetStringa.getStringa(dimensione, i+1));
			mediator.manageEvent(new ActionEvent(v[i], Counter.generateID(), null));
			pannello.add(v[i]);
		}
		return pannello;
	}

}
