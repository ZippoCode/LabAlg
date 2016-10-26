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
	private int dimWidth, dimHeigth;

	public Gui() {
		mediator = new GuiMediator();
	}

	public void avviaGui() {
		setTitle("Kenken");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		dimWidth = dimension.width / 2;
		dimHeigth = (dimension.height * 9) / 10;
		dimension.setSize(dimWidth, dimHeigth);
		setJMenuBar(new JPanelMenu(mediator, dimension));
		jpg = new JPanelMain(mediator, dimension);
		jpc = new JPanelComandi(mediator, dimension);
		mediator.manageEvent(new ActionEvent(jpg, Counter.generateID(), null));
		layout = new BorderLayout();
		setLayout(layout);
		add(jpc, BorderLayout.SOUTH);
		add(jpg, BorderLayout.CENTER);
		setSize(dimension);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}