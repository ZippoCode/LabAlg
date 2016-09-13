package progetto.command;

public abstract class AbstractCommand {
	
	public final static CommandManager manager= new CommandManager();
	public abstract boolean doIt();
	public abstract boolean undoIt();
	
}
