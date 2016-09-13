package progetto.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import progetto.mediator.GuiMediator;
import progetto.mediator.Mediator;
import progetto.utility.Counter;

/**
 * 
 * @author Salvatore
 * @version 1.0.0
 * 
 */

/*
 * GUI Rappresenta l'interfaccia grafica principale del Kenken
 */
public class Gui extends JFrame {

	private static final long serialVersionUID = 4728769432669358101L;
	private BorderLayout layout = null;
	private JGrigliaPanel jgp = null;
	private JComandiPanel jcp = null;
	private Mediator mediator = null;

	public Gui() {
		mediator = new GuiMediator();
	}

	public void avviaGui() {
		setJMenuBar(new JMenuPanel(mediator));
		jgp = new JGrigliaPanel(mediator);
		jgp.setName("jgrigliapanel");
		jcp = new JComandiPanel(mediator);
		mediator.manageEvent(new ActionEvent(jgp, Counter.generateID(), null));
		layout = new BorderLayout();
		setLayout(layout);
		// Aggiunta Pannelli
		add(jcp, BorderLayout.SOUTH);
		add(jgp, BorderLayout.CENTER);

		setTitle("Kenken");
		setLocation(500, 50);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 670);
		setResizable(false);
		setVisible(true);
	}
}