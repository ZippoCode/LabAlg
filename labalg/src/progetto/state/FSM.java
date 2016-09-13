package progetto.state;

public class FSM {

	private State currentState;

	public void transition(State nextState) {
		if (currentState != null) {
			currentState.exitState();
		}
		currentState = nextState;
		currentState.entryState();
	}

	public State currentState() {
		return currentState;
	}
}
