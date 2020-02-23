package api.state;

import api.model.Transfer;

public abstract class State {

	Transfer transfer;

	State(Transfer transfer) {
		this.transfer = transfer;
	}

	public abstract String onPending();

	public abstract String onCompleted();
}
