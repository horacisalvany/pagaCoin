package api.state;

import api.model.Transfer;

public class PendingState extends State {

	PendingState(Transfer transfer) {
		super(transfer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String onPending() {
		return "Pending ...";
	}

	@Override
	public String onCompleted() {
		this.transfer.changeState(new CompletedState(transfer));
		return "Completed ...";
	}
}
