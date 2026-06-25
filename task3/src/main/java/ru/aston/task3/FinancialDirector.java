package ru.aston.task3;

public class FinancialDirector extends PurchaseApprover {
	public FinancialDirector() {
		super(-1.);
	}

	@Override
	protected String doApprove(PurchaseRequest purchaseRequest) {
		return "[FinancialDirector][Approved] %s for %f".formatted(
				purchaseRequest.getPurpose(),
				purchaseRequest.getAmount()
		);
	}
}
