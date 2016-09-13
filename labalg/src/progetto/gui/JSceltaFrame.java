package progetto.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import progetto.mediator.Mediator;
import progetto.utility.Counter;

public class JSceltaFrame extends JFrame {
	private static final long serialVersionUID = -6076695279750416625L;
	private JRadioButton tre, quattro, cinque, sei, sette, otto, nove;
	private ButtonGroup dim = null;
	private JButton conferma = null;

	public JSceltaFrame(Mediator mediator) {
		setName("jsceltaframe");
		JPanel panel = (JPanel) getContentPane();
		panel.setLayout(new FlowLayout());
		tre = new JRadioButton("3");
		tre.setName("3");
		tre.setSelected(true);
		quattro = new JRadioButton("4");
		quattro.setName("4");
		cinque = new JRadioButton("5");
		cinque.setName("5");
		sei = new JRadioButton("6");
		sei.setName("6");
		sette = new JRadioButton("7");
		sette.setName("7");
		otto = new JRadioButton("8");
		otto.setName("8");
		nove = new JRadioButton("9");
		nove.setName("9");
		mediator.manageEvent(new ActionEvent(tre, Counter.generateID(), null));
		mediator.manageEvent(new ActionEvent(quattro, Counter.generateID(), null));
		mediator.manageEvent(new ActionEvent(cinque, Counter.generateID(), null));
		mediator.manageEvent(new ActionEvent(sei, Counter.generateID(), null));
		mediator.manageEvent(new ActionEvent(sette, Counter.generateID(), null));
		mediator.manageEvent(new ActionEvent(otto, Counter.generateID(), null));
		mediator.manageEvent(new ActionEvent(nove, Counter.generateID(), null));

		dim = new ButtonGroup();
		dim.add(tre);
		dim.add(quattro);
		dim.add(cinque);
		dim.add(sei);
		dim.add(sette);
		dim.add(otto);
		dim.add(nove);

		panel.add(tre);
		panel.add(quattro);
		panel.add(cinque);
		panel.add(sei);
		panel.add(sette);
		panel.add(otto);
		panel.add(nove);

		conferma = new JButton("CONFERMA");
		conferma.setName("conferma");
		mediator.manageEvent(new ActionEvent(conferma, Counter.generateID(), null));
		panel.add(conferma);
		pack();
		setTitle("Scegli dimensione");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(300, 100);
		setLocation(500, 200);
		setVisible(true);
	}

}