package progetto.command;

import java.util.LinkedList;

/**
 * 
 * @author Salvatore
 * @version 1.0.0
 */

public class CommandManager {
	private int maxHistoryLength = 100;

	private LinkedList<AbstractCommand> history = new LinkedList<AbstractCommand>();
	private LinkedList<AbstractCommand> redoList = new LinkedList<AbstractCommand>();

	public void invokeCommand(AbstractCommand cmd) {
		if (cmd instanceof Indietro) {
			indietro();
			return;
		}
		if (cmd instanceof Avanti) {
			avanti();
			return;
		}
		if (cmd.doIt()) {
			// Se restiusce TRUE: il valore può essere annullato
			addToHistory(cmd);
		} else {
			// Se restituisce FALSE: il valore non può essere annullato
			history.clear();
		}
		if (redoList.size() > 0)
			redoList.clear();
	}

	private void avanti() {
		if (redoList.size() > 0) {
			AbstractCommand redoCmd = redoList.removeFirst();
			redoCmd.doIt();
			history.addFirst(redoCmd);

		}
	}

	private void indietro() {
		if (history.size() > 0) {
			AbstractCommand undoCmd = history.removeFirst();
			undoCmd.undoIt();
			redoList.addFirst(undoCmd);
		}
	}

	private void addToHistory(AbstractCommand cmd) {
		history.addFirst(cmd);
		if (history.size() > maxHistoryLength) {
			history.removeLast();
		}

	}

}
