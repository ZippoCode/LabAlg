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
import progetto.builder.Mappa;
import progetto.command.AvantiCommand;
import progetto.command.IndietroCommand;
import progetto.command.WriteCommand;
import progetto.gui.JPanelCella;
import progetto.gui.JPanelMain;
import progetto.gui.JFrameIstruzioni;
import progetto.gui.JFrameRisolto;
import progetto.gui.JFrameScelta;
import progetto.state.FSM;
import progetto.state.State;
import progetto.template_method.Kenken;
import progetto.utility.GestoreTesto;
import progetto.utility.GetStringa;
import progetto.utility.Posizione;

/**
 * 
 * @author Salvatore
 * @version 2.0.0
 */
public class GuiMediator extends FSM implements Mediator, Runnable {

	private final State START = new StartState();
	private final State SCELTA = new SceltaState();
	private final State RELAX = new RelaxState();
	private final State USCITA = new UscitaState();
	private final State BUILDER = new BuilderState();
	private final State GIOCA = new GiocaState();
	private final State RISOLTO = new RisoltoState();

	private MediatorAdapter adapter = new MediatorAdapter();
	private JButton risolvi, reset, avanti, indietro, check, saveState, restoreState, help;
	private JButton conferma, annulla;
	private JButton play, noplay = null;
	private JButton introGame, introInfo, introContatti, introEsci;
	private JRadioButton tre, quattro, cinque, sei, sette, otto, nove;
	private JMenuItem gioca, exit, istruzioni, contatti, comandi;
	private String dim = null;
	private JFrameScelta jsf = null;
	private JFrameRisolto jrf = null;
	private JPanelCella cella;
	private JPanelMain jPanelMain = null;
	private boolean grigliaPresente = false;
	private Kenken kenken = null;
	private int dimMappa = -1;
	private Mappa mappa = null;
	private HashMap<String, JPanelCella> mappaCelle = new HashMap<>();
	private String testo = "CLICCARE SU GIOCA E SELEZIONARE LA DIMENSIONE DELLA GRIGLIA,"
			+ "\nPER SCRIVERE UN NUMERO E' SUFFICIENTIE CLICCARE SULLA POSIZIONE\n"
			+ "E DIGITARE DA TASTIERA IL NUMERO SCELTO.\nI BOTTONI\nRISOLVI : Visualizza la soluzione\n"
			+ "RESET : Elimina i numeri scritti\n>> e << : Permettono di navigare sui numeri scritti\n"
			+ "CHECK : Informa se i numeri scritti sono corretti\n"
			+ "SAVE e RESTORE : Permetto di salvare e ripristinare lo stato\n"
			+ "AIUTO : Visualizza un numero in una posizione casuale ";

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
		private int COUNTER = 29;

		@Override
		public void entryState() {
			jsf = new JFrameScelta(this);
			jrf = new JFrameRisolto(this);
		}

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
				break;
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
				check.setEnabled(false);
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
			case "3":
				tre = (JRadioButton) component;
				tre.addActionListener(adapter);
				tre.setSelected(true);
				break;
			case "4":
				quattro = (JRadioButton) component;
				quattro.addActionListener(adapter);
				quattro.setSelected(false);
				break;
			case "5":
				cinque = (JRadioButton) component;
				cinque.addActionListener(adapter);
				cinque.setSelected(false);
				break;
			case "6":
				sei = (JRadioButton) component;
				sei.setSelected(false);
				break;
			case "7":
				sette = (JRadioButton) component;
				sette.addActionListener(adapter);
				sette.setSelected(false);
				break;
			case "8":
				otto = (JRadioButton) component;
				otto.addActionListener(adapter);
				otto.setSelected(false);
				break;
			case "9":
				nove = (JRadioButton) component;
				nove.addActionListener(adapter);
				nove.setSelected(false);
				break;
			case "conferma":
				conferma = (JButton) component;
				conferma.addActionListener(adapter);
				break;
			case "annulla":
				annulla = (JButton) component;
				annulla.addActionListener(adapter);
				break;
			case "play":
				play = (JButton) component;
				play.addActionListener(adapter);
				break;
			case "noplay":
				noplay = (JButton) component;
				noplay.addActionListener(adapter);
				break;
			case "introGame":
				introGame = (JButton) component;
				introGame.addActionListener(adapter);
				break;
			case "introInfo":
				introInfo = (JButton) component;
				introInfo.addActionListener(adapter);
				break;
			case "introContatti":
				introContatti = (JButton) component;
				introContatti.addActionListener(adapter);
				break;
			case "introEsci":
				introEsci = (JButton) component;
				introEsci.addActionListener(adapter);
				break;
			default:
				jPanelMain = (JPanelMain) component;
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
			String nomeEvento = ((JComponent) event.getSource()).getName();
			if (nomeEvento.equals("gioca") || nomeEvento.equals("introGame"))
				transition(SCELTA);
			else if (nomeEvento.equals("exit") || nomeEvento.equals("introEsci"))
				transition(USCITA);
			else if (nomeEvento.equals("istruzioni") || nomeEvento.equals("introInfo"))
				new JFrameIstruzioni();
			else if (nomeEvento.equals("comandi"))
				JOptionPane.showMessageDialog(null, testo, "Comandi", JOptionPane.INFORMATION_MESSAGE);
			else if (nomeEvento.equals("contatti") || nomeEvento.equals("introContatti"))
				JOptionPane.showMessageDialog(null,
						"Studente: Salvatore Prochilo\nMatricola: 150097\nE-mail: prochilo.salvatore@gmail.com", "Info",
						JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private class SceltaState extends MediatorState {

		@Override
		public void entryState() {
			jsf.setVisible(true);
		}

		@Override
		public void exitState() {
			jPanelMain.changeFlag();
			jsf.setVisible(false);
		}

		@Override
		public void manageEvent(ActionEvent event) {
			String name = ((JComponent) event.getSource()).getName();
			if (name.equals("conferma")) {
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
				transition(BUILDER);
			} else if (name.equals("annulla") && grigliaPresente) {
				transition(GIOCA);
			} else if (name.equals("annulla") && !grigliaPresente) {
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
			mappa = bm.getMappa();
			dimMappa = mappa.getDimensioneMappa();
			kenken = new Kenken(mappa);
			jPanelMain.settaBordo(nome);
			jPanelMain.creaJPanelGriglia(mappa.getInsiemeBlocchi(), dimMappa);
			run();
			transition(GIOCA);
		}

		@Override
		public void exitState() {
			risolvi.setEnabled(true);
			avanti.setEnabled(false);
			indietro.setEnabled(false);
			check.setEnabled(false);
			saveState.setEnabled(false);
			restoreState.setEnabled(false);
			help.setEnabled(true);
			grigliaPresente = true;
		}

		@Override
		public void manageEvent(ActionEvent event) {
			JComponent component = (JComponent) event.getSource();
			cella = (JPanelCella) component;
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
					jPanelMain.visualizzaSoluzione(mappa.getSoluzione());
					transition(RELAX);
				}
				break;
			case "reset":
				mappa.resettaMappa();
				jPanelMain.restart();
				break;
			case "avanti":
				new AvantiCommand();
				break;
			case "indietro":
				new IndietroCommand();
				break;
			case "check":
				jPanelMain.checkSoluzione(mappa.posizioniCorrette(), mappa.posizioniScorrette());
				break;
			case "saveState":
				mappa.salvaIstanza();
				restoreState.setEnabled(true);
				JOptionPane.showMessageDialog(null, "Lo stato è stato salvato", "Conferma",
						JOptionPane.INFORMATION_MESSAGE);
				break;
			case "restoreState":
				Mappa ripristino = mappa.ripristinaIstanza();
				jPanelMain.ripristinaStato(ripristino);
				JOptionPane.showMessageDialog(null, "Lo stato è stato ripristinato", "Conferma",
						JOptionPane.INFORMATION_MESSAGE);
				break;
			case "help":
				Posizione posizione = mappa.getPosizioneRandom();
				mappa.write(mappa.getSoluzione().getValore(posizione), posizione);
				jPanelMain.aiutalo(posizione, mappa.getSoluzione().getValore(posizione));
				if (mappa.equals(mappa.getSoluzione())) {
					help.setEnabled(false);
					transition(RISOLTO);
				}
				break;
			case "gioca":
				transition(SCELTA);
				break;
			case "istruzioni":
				new JFrameIstruzioni();
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
					JPanelCella jc = mappaCelle.get(((JComponent) event.getSource()).getName());
					jc.addKeyListener(new KeyAdapter() {
						@Override
						public void keyTyped(KeyEvent ke) {
							String value = String.valueOf(ke.getKeyChar());
							if (jPanelMain.possoScriverlo(value, dimMappa)) {
								check.setEnabled(true);
								saveState.setEnabled(true);
								reset.setEnabled(true);
								new WriteCommand(jc, value, mappa, jPanelMain);
							}
							if (mappa.equals(mappa.getSoluzione())) {
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
			risolvi.setEnabled(false);
			reset.setEnabled(false);
			avanti.setEnabled(false);
			indietro.setEnabled(false);
			check.setEnabled(false);
			saveState.setEnabled(false);
			restoreState.setEnabled(false);
			help.setEnabled(false);
			jrf.setVisible(true);
			grigliaPresente = false;
		}

		@Override
		public void exitState() {
			jrf.setVisible(false);
		}

		@Override
		public void manageEvent(ActionEvent event) {
			if (((JComponent) event.getSource()).getName().equals("play")) {
				jrf.setVisible(false);
				transition(SCELTA);
			} else if (((JComponent) event.getSource()).getName().equals("noplay"))
				transition(USCITA);
		}
	}

	private class MediatorAdapter implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg) {
			manageEvent(arg);
		}

	}

	@Override
	public void run() {
		kenken.risolvi();
	}
}
