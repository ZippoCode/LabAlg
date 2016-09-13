package progetto.mediator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import progetto.builder.BuilderMappa;
import progetto.builder.Director;
import progetto.command.AvantiCommand;
import progetto.command.IndietroCommand;
import progetto.command.WriteCommand;
import progetto.gui.JCella;
import progetto.gui.JGrigliaPanel;
import progetto.gui.JIstruzioniFrame;
import progetto.gui.JSceltaFrame;
import progetto.state.FSM;
import progetto.state.State;
import progetto.utility.GestoreTesto;
import progetto.utility.GetStringa;
import progetto.utility.Posizione;

/**
 * 
 * @author Salvatore
 * @version 1.1.6
 */
public class GuiMediator extends FSM implements Mediator {

	private final State START = new StartState();
	private final State SCELTA = new SceltaState();
	private final State RELAX = new RelaxState();
	private final State USCITA = new UscitaState();
	private final State BUILDER = new BuilderState();
	private final State GIOCA = new GiocaState();
	private final State RISOLTO = new RisoltoState();

	private MediatorAdapter adapter = new MediatorAdapter();
	private JButton risolvi, reset, avanti, indietro, check, saveState, restoreState, help;
	private JButton conferma;
	private JRadioButton tre, quattro, cinque, sei, sette, otto, nove;
	private JMenuItem gioca, exit, istruzioni, contatti, comandi;
	private String dim = null;
	private JSceltaFrame jsf = null;
	private JCella cella;
	private JGrigliaPanel jGrigliaPanel = null;
	private HashMap<String, JCella> mappaCelle = new HashMap<>();
	private String testo = "CLICCARE SU GIOCA E SELEZIONARE LA DIMENSIONE DELLA GRIGLIA,\nPER SCRIVERE UN NUMERO E' SUFFICIENTIE CLICCARE SULLA POSIZIONE\nE DIGITARE DA TASTIERA IL NUMERO SCELTO.\nI BOTTONI\nRISOLVI : Visualizza la soluzione\nRESET : Elimina i numeri scritti\nAVANTI e INDIETRO : Permettono di navigare sui numeri scritti\nCHECK : Informa se i numeri scritti sono corretti";

	public GuiMediator() {
		transition(START);
	}

	@Override
	public void manageEvent(ActionEvent event) {
		((MediatorState) currentState()).manageEvent(event);
	}

	private class MediatorState extends State implements Mediator {
		public void manageEvent(ActionEvent event) {
		}
	}

	private class StartState extends MediatorState {
		private int COUNTER = 14;

		public void manageEvent(ActionEvent event) {
			JComponent component = (JComponent) event.getSource();
			String name = component.getName();
			switch (name) {
			case "risolvi":
				risolvi = (JButton) component;
				risolvi.addActionListener(adapter);
				risolvi.setEnabled(false);
				break;
			case "reset":
				reset = (JButton) component;
				reset.addActionListener(adapter);
				reset.setEnabled(false);
			case "avanti":
				avanti = (JButton) component;
				avanti.addActionListener(adapter);
				avanti.setEnabled(false);
				break;
			case "indietro":
				indietro = (JButton) component;
				indietro.addActionListener(adapter);
				indietro.setEnabled(false);
				break;
			case "check":
				check = (JButton) component;
				check.addActionListener(adapter);
				break;
			case "saveState":
				saveState = (JButton) component;
				saveState.addActionListener(adapter);
				saveState.setEnabled(false);
				break;
			case "restoreState":
				restoreState = (JButton) component;
				restoreState.addActionListener(adapter);
				restoreState.setEnabled(false);
				break;
			case "help":
				help = (JButton) component;
				help.addActionListener(adapter);
				help.setEnabled(false);
				break;
			case "gioca":
				gioca = (JMenuItem) component;
				gioca.addActionListener(adapter);
				break;
			case "exit":
				exit = (JMenuItem) component;
				exit.addActionListener(adapter);
				break;
			case "istruzioni":
				istruzioni = (JMenuItem) component;
				istruzioni.addActionListener(adapter);
				break;
			case "comandi":
				comandi = (JMenuItem) component;
				comandi.addActionListener(adapter);
				break;
			case "contatti":
				contatti = (JMenuItem) component;
				contatti.addActionListener(adapter);
				break;
			default:
				jGrigliaPanel = (JGrigliaPanel) component;
				break;
			}
			COUNTER--;
			if (COUNTER == 0) {
				transition(RELAX);
			}
		}
	}

	private class RelaxState extends MediatorState {
		@Override
		public void entryState() {
			risolvi.setEnabled(false);
			reset.setEnabled(false);
			avanti.setEnabled(false);
			indietro.setEnabled(false);
			check.setEnabled(false);
			saveState.setEnabled(false);
			restoreState.setEnabled(false);
			help.setEnabled(false);
		}

		@Override
		public void manageEvent(ActionEvent event) {
			if (((JComponent) event.getSource()).getName().equals("gioca")) {
				transition(SCELTA);
			} else if (((JComponent) event.getSource()).getName().equals("exit")) {
				transition(USCITA);
			} else if (((JComponent) event.getSource()).getName().equals("istruzioni")) {
				new JIstruzioniFrame();
			} else if (((JComponent) event.getSource()).getName().equals("comandi")) {
				JOptionPane.showMessageDialog(null, testo, "Comandi", JOptionPane.INFORMATION_MESSAGE);
			} else if (((JComponent) event.getSource()).getName().equals("contatti")) {
				JOptionPane.showMessageDialog(null,
						"Studente: Salvatore Prochilo\nMatricola: 150097\nE-mail: prochilo.salvatore@gmail.com", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			} else if (((JComponent) event.getSource()).getName().equals("conferma")) {
				if (tre.isSelected()) {
					dim = "3";
				} else if (quattro.isSelected()) {
					dim = "4";
				} else if (cinque.isSelected()) {
					dim = "5";
				} else if (sei.isSelected()) {
					dim = "6";
				} else if (sette.isSelected()) {
					dim = "7";
				} else if (otto.isSelected()) {
					dim = "8";
				} else if (nove.isSelected()) {
					dim = "9";
				}
				jsf.dispose();
				transition(BUILDER);
			}
		}
	}

	private class SceltaState extends MediatorState {

		@Override
		public void entryState() {
			jsf = new JSceltaFrame(this);
		}

		@Override
		public void manageEvent(ActionEvent event) {
			JComponent component = (JComponent) event.getSource();
			String name = component.getName();
			if (name.equals("3")) {
				tre = (JRadioButton) component;
				tre.addActionListener(adapter);
				tre.setSelected(true);
			} else if (name.equals("4")) {
				quattro = (JRadioButton) component;
				quattro.addActionListener(adapter);
				quattro.setSelected(false);
			} else if (name.equals("5")) {
				cinque = (JRadioButton) component;
				cinque.addActionListener(adapter);
				cinque.setSelected(false);
			} else if (name.equals("6")) {
				sei = (JRadioButton) component;
				sei.setSelected(false);
			} else if (name.equals("7")) {
				sette = (JRadioButton) component;
				sette.addActionListener(adapter);
				sette.setSelected(false);
			} else if (name.equals("8")) {
				otto = (JRadioButton) component;
				otto.addActionListener(adapter);
				otto.setSelected(false);
			} else if (name.equals("9")) {
				nove = (JRadioButton) component;
				nove.addActionListener(adapter);
				nove.setSelected(false);
			} else if (name.equals("conferma")) {
				conferma = (JButton) component;
				conferma.addActionListener(adapter);
				transition(RELAX);
			}
		}
	}

	private class UscitaState extends MediatorState {
		@Override
		public void entryState() {
			int i = JOptionPane.showConfirmDialog(null, "Vuoi uscire?", "SICURO?", JOptionPane.YES_NO_OPTION);
			if (i == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, "Premi OK per uscire", "Bye", JOptionPane.CLOSED_OPTION);
				System.exit(0);
			} else {
				transition(RELAX);
			}
		}
	}

	private class BuilderState extends MediatorState {
		@Override
		public void entryState() {
			String nome = GetStringa.getName(Integer.parseInt(dim));
			GestoreTesto gt = new GestoreTesto(nome);
			BuilderMappa bm = new BuilderMappa();
			Director dir = new Director(bm);
			dir.buildInsiemeBlocchi(gt);
			dir.creaGriglia(Integer.parseInt(dim));
			jGrigliaPanel.settaBordo(nome);
			jGrigliaPanel.creaJGrigliaPanel(bm.getMappa());
			Thread t = new Thread(jGrigliaPanel);
			t.start();
			transition(GIOCA);
		}

		@Override
		public void exitState() {
			risolvi.setEnabled(true);
			reset.setEnabled(true);
			avanti.setEnabled(false);
			indietro.setEnabled(false);
			check.setEnabled(true);
			saveState.setEnabled(true);
			restoreState.setEnabled(false);
			help.setEnabled(true);
		}

		@Override
		public void manageEvent(ActionEvent event) {
			JComponent component = (JComponent) event.getSource();
			cella = (JCella) component;
			cella.addActionListener(adapter);
			mappaCelle.put(cella.getName(), cella);
		}
	}

	private class GiocaState extends MediatorState {

		@Override
		public void manageEvent(ActionEvent event) {
			switch (((JComponent) event.getSource()).getName()) {
			case "risolvi":
				int risposta = JOptionPane.showConfirmDialog(null,
						"Vuoi vedere la soluzione?\nATTENZIONE NON SARA' POSSIBILE CONTINUARE A GIOCARE", "Conferma",
						JOptionPane.YES_OPTION);
				if (risposta == JOptionPane.YES_OPTION) {
					jGrigliaPanel.visualizzaSoluzione();
					transition(RELAX);
				}
				break;
			case "reset":
				jGrigliaPanel.restart();
				break;
			case "avanti":
				new AvantiCommand();
				break;
			case "indietro":
				new IndietroCommand();
				break;
			case "check":
				jGrigliaPanel.checkSoluzione();
				break;
			case "saveState":
				jGrigliaPanel.salvaStato();
				restoreState.setEnabled(true);
				JOptionPane.showMessageDialog(null, "Lo stato è stato salvato", "Conferma",
						JOptionPane.INFORMATION_MESSAGE);
				break;
			case "restoreState":
				jGrigliaPanel.ripristinaStato();
				JOptionPane.showMessageDialog(null, "Lo stato è stato ripristinato", "Conferma",
						JOptionPane.INFORMATION_MESSAGE);
				break;
			case "help":
				Posizione posizione = jGrigliaPanel.posRandom();
				jGrigliaPanel.aiutalo(posizione);
				if (jGrigliaPanel.soluzioneCompleta())
					transition(RISOLTO);
				break;
			case "gioca":
				transition(SCELTA);
				break;
			case "istruzioni":
				new JIstruzioniFrame();
				break;
			case "comandi":
				JOptionPane.showMessageDialog(null, testo, "Comandi", JOptionPane.INFORMATION_MESSAGE);
				break;
			case "exit":
				transition(USCITA);
				break;
			case "contatti":
				JOptionPane.showMessageDialog(null,
						"Studente: Salvatore Prochilo\nMatricola: 150097\nE-mail: prochilo.salvatore@gmail.com", "Info",
						JOptionPane.INFORMATION_MESSAGE);
				break;
			default:
				if (mappaCelle.containsKey(((JComponent) event.getSource()).getName())) {
					JCella jc = mappaCelle.get(((JComponent) event.getSource()).getName());
					jc.addKeyListener(new KeyAdapter() {
						@Override
						public void keyTyped(KeyEvent ke) {
							String value = String.valueOf(ke.getKeyChar());
							if (jGrigliaPanel.possoScriverlo(value)) {
								new WriteCommand(jc, value, jGrigliaPanel);
							}
							if (jGrigliaPanel.soluzioneCompleta()) {
								transition(RISOLTO);
							}
						}
					});
					avanti.setEnabled(true);
					indietro.setEnabled(true);
				}
				break;
			}
		}
	}

	private class RisoltoState extends MediatorState {
		@Override
		public void entryState() {
			int i = JOptionPane.showConfirmDialog(null, "Vuoi iniziare una nuova partita", "NUOVA PARTITA?",
					JOptionPane.YES_NO_OPTION);
			if (i == JOptionPane.YES_OPTION)
				transition(SCELTA);
			else
				transition(USCITA);
		}
	}

	private class MediatorAdapter implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg) {
			manageEvent(arg);
		}
	}
}
