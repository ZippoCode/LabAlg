package progetto.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import progetto.mediator.GuiMediator;
import progetto.mediator.Mediator;
import progetto.utility.Counter;

/**
 * Interfaccia principale
 * 
 * @author Salvatore
 * @version 2.0
 * 
 */

public class Gui extends JFrame {

	private static final long serialVersionUID = 4728769432669358101L;
	private BorderLayout layout = null;
	private JPanelMain jpg = null;
	private JPanelComandi jpc = null;
	private Mediator mediator = null;
	private Dimension dimensione = null;

	public Gui() {
		mediator = new GuiMediator();
	}

	public void avviaGui() {
		setJMenuBar(new JPanelMenu(mediator));
		jpg = new JPanelMain(mediator);
		jpc = new JPanelComandi(mediator);

		mediator.manageEvent(new ActionEvent(jpg, Counter.generateID(), null));
		layout = new BorderLayout();
		setLayout(layout);
		// Aggiunta Pannelli
		add(jpc, BorderLayout.SOUTH);
		add(jpg, BorderLayout.CENTER);

		setTitle("Kenken");
		dimensione = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(dimensione.width-800, dimensione.height-120);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}