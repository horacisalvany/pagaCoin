package api.state;

import api.model.Transfer;

public class CompletedState extends State {

	CompletedState(Transfer transfer) {
		super(transfer);
	}

	@Override
	public String onPending() {
		return "Completed ...";
	}

	@Override
	public String onCompleted() {
		return "Completed ...";
	}
}
