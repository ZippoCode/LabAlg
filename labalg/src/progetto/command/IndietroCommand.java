package progetto.command;


public class IndietroCommand extends AbstractCommand implements Indietro {

	public IndietroCommand() {
		manager.invokeCommand(this);
	}

	@Override
	public boolean doIt() {
		throw new NoSuchMethodError();
	}

	@Override
	public boolean undoIt() {
		throw new NoSuchMethodError();
	}

}
