package progetto.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import progetto.mediator.Mediator;
import progetto.utility.Counter;

/**
 * 
 * @author Salvatore
 * @version 1.0.1
 */
/*
 * JMenuPanel gestisce il pannello dei menu situato in alto nella GUI Ha tre
 * JMenu con i quali si possono scegliere differenti opzioni
 * 
 */
public class JPanelMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private JMenu nuovo = null;
	private JMenu help = null;
	private JMenu info = null;
	private JMenuItem play = null;
	private JMenuItem playRandom = null;
	private JMenuItem exit = null;
	private JMenuItem istruzioni = null;
	private JMenuItem contatti = null;
	private JMenuItem comandi = null;

	public JPanelMenu(Mediator mediator, Dimension dimension) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Font f = new Font("Ariel", Font.BOLD, dim.height/50);
		setBackground(Color.decode("#01579B"));
		setBorder(BorderFactory.createLineBorder(Color.BLUE));
		nuovo = new JMenu("Nuova partita");
		nuovo.setName("nuovo");
		nuovo.setFont(f);
		nuovo.setForeground(Color.WHITE);
		help = new JMenu("Aiuto");
		help.setName("Help");
		help.setFont(f);
		help.setForeground(Color.WHITE);
		info = new JMenu("Info");
		info.setName("info");
		info.setFont(f);
		info.setForeground(Color.WHITE);
		comandi = new JMenuItem("Comandi");
		comandi.setName("comandi");
		comandi.setFont(f);
		play = new JMenuItem("Selezione griglia");
		play.setName("gioca");
		play.setFont(f);
		playRandom = new JMenuItem("Selezione griglia casuale");
		playRandom.setName("giocaRandom");
		playRandom.setFont(f);
		exit = new JMenuItem("Exit");
		exit.setName("exit");
		exit.setFont(f);
		nuovo.add(play);
		nuovo.add(playRandom);
		nuovo.addSeparator();
		nuovo.add(exit);
		istruzioni = new JMenuItem("Istruzioni");
		istruzioni.setName("istruzioni");
		istruzioni.setFont(f);
		help.add(istruzioni);
		help.addSeparator();
		help.add(comandi);
		contatti = new JMenuItem("Contatti");
		contatti.setName("contatti");
		contatti.setFont(f);
		info.add(contatti);
		add(nuovo);
		add(help);
		add(info);
		mediator.manageEvent(new ActionEvent(play, Counter.generateID(), null));
		mediator.manageEvent(new ActionEvent(playRandom, Counter.generateID(), null));
		mediator.manageEvent(new ActionEvent(exit, Counter.generateID(), null));
		mediator.manageEvent(new ActionEvent(istruzioni, Counter.generateID(), null));
		mediator.manageEvent(new ActionEvent(contatti, Counter.generateID(), null));
		mediator.manageEvent(new ActionEvent(comandi, Counter.generateID(), null));
	}

}
