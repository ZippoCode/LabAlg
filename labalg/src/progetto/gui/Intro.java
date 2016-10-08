package progetto.gui;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 
 * @author Zippo
 * @version 1.0.0
 * 
 */
public class Intro extends JPanel {

	/**
	 * Questa classe viene utilizzata all'avvio dell'applicazione è presenta
	 * alcuni bottoni per guidare l'utente nelle scelte
	 * 
	 */
	private static final long serialVersionUID = -8501079676773034850L;
	public JButton gioca = null;
	public JButton istruzioni = null;
	public JButton contatti = null;
	public JButton esci = null;

	public Intro() {
		gestioneBottone(gioca, "Gioca");
		gestioneBottone(istruzioni, "Istruzioni");
		gestioneBottone(contatti, "Contatti");
		gestioneBottone(esci, "Esci");
	}

	private void gestioneBottone(JButton button, String nome) {
		button = new JButton(nome);
		button.setText(nome);
		add(button);
	}

}
