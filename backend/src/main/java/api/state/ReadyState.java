package api.state;

import api.model.Transfer;

public class ReadyState extends State {

	public ReadyState(Transfer transfer) {
		super(transfer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String onPending() {
		this.transfer.changeState(new PendingState(transfer));
		return "Pending...";
	}

	@Override
	public String onCompleted() {
		return "Ready ...";
	}
}
