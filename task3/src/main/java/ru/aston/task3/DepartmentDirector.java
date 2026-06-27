package ru.aston.task3;


public class DepartmentDirector extends PurchaseApprover {
	public DepartmentDirector() {
		super(50000.);
	}

	@Override
	protected String doApprove(PurchaseRequest purchaseRequest) {
		return "[DepartmentDirector][Approved] %s for %f".formatted(
				purchaseRequest.getPurpose(),
				purchaseRequest.getAmount()
		);
	}
}
