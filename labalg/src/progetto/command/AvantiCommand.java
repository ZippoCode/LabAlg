package progetto.command;


public class AvantiCommand extends AbstractCommand implements Avanti {

	public AvantiCommand() {
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
