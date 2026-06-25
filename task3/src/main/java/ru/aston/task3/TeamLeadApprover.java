package ru.aston.task3;

public class TeamLeadApprover extends PurchaseApprover {
	public TeamLeadApprover() {
		super(500.0);
	}

	@Override
	protected String doApprove(PurchaseRequest purchaseRequest) {
		return "[TeamLead][Approved] %s for %f".formatted(purchaseRequest.getPurpose(), purchaseRequest.getAmount());
	}
}
