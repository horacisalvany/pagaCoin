package api.state;

public class StateContext {

	private State currentState;

	public StateContext(State currentState) {
		super();
		this.currentState = currentState;

		if (currentState == null) {
		}
	}

}
