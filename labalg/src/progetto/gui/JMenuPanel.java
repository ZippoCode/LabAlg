package progetto.gui;

import java.awt.Color;
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
public class JMenuPanel extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private JMenu nuovo = null;
	private JMenu help = null;
	private JMenu info = null;
	private JMenuItem play = null;
	private JMenuItem exit = null;
	private JMenuItem istruzioni = null;
	private JMenuItem contatti = null;
	private JMenuItem comandi = null;

	public JMenuPanel(Mediator mediator) {
		setBackground(Color.decode("#FFFFFF"));
		setBorder(BorderFactory.createLineBorder(Color.BLUE));
		nuovo = new JMenu("Nuova partita");
		nuovo.setName("nuovo");
		help = new JMenu("Aiuto");
		help.setName("Help");
		info = new JMenu("Info");
		info.setName("info");
		comandi = new JMenuItem("Comandi");
		comandi.setName("comandi");
		play = new JMenuItem("Selezione griglia");
		play.setName("gioca");
		exit = new JMenuItem("Exit");
		exit.setName("exit");
		nuovo.add(play);
		nuovo.addSeparator();
		nuovo.add(exit);
		istruzioni = new JMenuItem("Istruzioni");
		istruzioni.setName("istruzioni");
		help.add(istruzioni);
		help.addSeparator();
		help.add(comandi);
		contatti = new JMenuItem("Contatti");
		contatti.setName("contatti");
		info.add(contatti);
		add(nuovo);
		add(help);
		add(info);
		mediator.manageEvent(new ActionEvent(play, Counter.generateID(), null));
		mediator.manageEvent(new ActionEvent(exit, Counter.generateID(), null));
		mediator.manageEvent(new ActionEvent(istruzioni, Counter.generateID(), null));
		mediator.manageEvent(new ActionEvent(contatti, Counter.generateID(), null));
		mediator.manageEvent(new ActionEvent(comandi, Counter.generateID(), null));
	}

}
