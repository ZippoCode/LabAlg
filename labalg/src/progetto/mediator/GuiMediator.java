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
import progetto.gui.JFrameComandi;
import progetto.gui.JFrameContatti;
import progetto.gui.JFrameIstruzioni;
import progetto.gui.JFrameRisolto;
import progetto.gui.JFrameScelta;
import progetto.gui.JFrameUscita;
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
	private final State JFRAME = new JFrameState();

	private MediatorAdapter adapter = new MediatorAdapter();
	private JButton risolvi, reset, avanti, indietro, check, saveState, restoreState, help;
	private JButton conferma, annulla;
	private JButton play, noplay = null;
	private JButton introGame, introInfo, introContatti, introEsci;
	private JRadioButton tre, quattro, cinque, sei, sette, otto, nove;
	private JButton siExit, noExit = null;
	private JButton okContatti = null, okComandi = null;
	private JMenuItem gioca, exit, istruzioni, contatti, comandi;
	private String dim = null;
	private JFrameScelta jFrameScelta = null;
	private JFrameRisolto jFrameRisolto = null;
	private JFrameUscita jFrameUscita = null;
	private JFrameContatti jFrameContatti = null;
	private JFrameComandi jFrameComandi = null;
	private JPanelCella cella;
	private JPanelMain jPanelMain = null;
	private boolean grigliaPresente = false, flag = false;
	private Kenken kenken = null;
	private int dimMappa = -1;
	private Mappa mappa = null;
	private HashMap<String, JPanelCella> mappaCelle = new HashMap<>();

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
		private int COUNTER = 33;

		@Override
		public void entryState() {
			jFrameScelta = new JFrameScelta(this);
			jFrameRisolto = new JFrameRisolto(this);
			jFrameUscita = new JFrameUscita(this);
			jFrameContatti = new JFrameContatti(this);
			jFrameComandi = new JFrameComandi(this);
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
			case "siExit":
				siExit = (JButton) component;
				siExit.addActionListener(adapter);
				break;
			case "noExit":
				noExit = (JButton) component;
				noExit.addActionListener(adapter);
				break;
			case "okContatti":
				okContatti = (JButton) component;
				okContatti.addActionListener(adapter);
				break;
			case "okComandi":
				okComandi = (JButton) component;
				okComandi.addActionListener(adapter);
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
			else if (nomeEvento.equals("comandi")) {
				flag = false;
				transition(JFRAME);
			} else if (nomeEvento.equals("contatti") || nomeEvento.equals("introContatti")) {
				flag = true;
				transition(JFRAME);
			}
		}
	}

	private class SceltaState extends MediatorState {

		@Override
		public void entryState() {
			jFrameScelta.setVisible(true);
		}

		@Override
		public void exitState() {
			jFrameScelta.setVisible(false);
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
		public void entryState() {
			jPanelMain.changeFlag();
		}

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
				jPanelMain.imbianca();
				break;
			case "restoreState":
				Mappa ripristino = mappa.ripristinaIstanza();
				jPanelMain.ripristinaStato(ripristino);
				JOptionPane.showMessageDialog(null, "Lo stato è stato ripristinato", "Conferma",
						JOptionPane.INFORMATION_MESSAGE);
				jPanelMain.imbianca();
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
				flag = false;
				transition(JFRAME);
				break;
			case "exit":
				transition(USCITA);
				break;
			case "contatti":
				flag = true;
				transition(JFRAME);
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
			jFrameRisolto.setVisible(true);
			grigliaPresente = false;
		}

		@Override
		public void exitState() {
			jFrameRisolto.setVisible(false);
		}

		@Override
		public void manageEvent(ActionEvent event) {
			if (((JComponent) event.getSource()).getName().equals("play")) {
				jFrameRisolto.setVisible(false);
				transition(SCELTA);
			} else if (((JComponent) event.getSource()).getName().equals("noplay"))
				transition(USCITA);
		}
	}

	private class UscitaState extends MediatorState {
		@Override
		public void entryState() {
			jFrameUscita.setVisible(true);
		}

		@Override
		public void exitState() {
			jFrameUscita.setVisible(false);
		}

		@Override
		public void manageEvent(ActionEvent event) {
			String name = ((JComponent) event.getSource()).getName();
			if (name.equals("siExit")) {
				jFrameUscita.messaggioDiUscita();
			} else if (name.equals("noExit") && grigliaPresente) {
				transition(GIOCA);
			} else if (name.equals("noExit") && !grigliaPresente) {
				transition(RELAX);
			}
		}
	}

	private class JFrameState extends MediatorState {
		@Override
		public void entryState() {
			if (flag)
				jFrameContatti.setVisible(true);
			else
				jFrameComandi.setVisible(true);
		}

		@Override
		public void exitState() {
			if (flag)
				jFrameContatti.setVisible(false);
			else
				jFrameComandi.setVisible(false);
		}

		@Override
		public void manageEvent(ActionEvent event) {
			String name = ((JComponent) event.getSource()).getName();
			if ((name.equals("okContatti") || name.endsWith("okComandi")) && grigliaPresente) {
				transition(GIOCA);
			} else if ((name.equals("okContatti") || name.endsWith("okComandi")) && !grigliaPresente) {
				transition(RELAX);
			}
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
