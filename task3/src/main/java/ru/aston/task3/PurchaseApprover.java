package ru.aston.task3;


import org.jetbrains.annotations.NotNull;

import java.util.Optional;


public abstract class PurchaseApprover {
	protected PurchaseApprover nextApprover;
	protected double maxApprovalAmount;

	public PurchaseApprover linkWith(@NotNull PurchaseApprover nextApprover) {
		this.nextApprover = nextApprover;
		return nextApprover;
	}

	public Optional<String> approve(PurchaseRequest purchaseRequest) {
		if (maxApprovalAmount < 0 || maxApprovalAmount >= purchaseRequest.getAmount()) {
			return Optional.of(doApprove(purchaseRequest));
		}
		else if (nextApprover != null) {
			return nextApprover.approve(purchaseRequest);
		}

		return Optional.empty();
	}

	protected abstract String doApprove(PurchaseRequest purchaseRequest);

	protected PurchaseApprover(double maxApprovalAmount) {
		this.maxApprovalAmount = maxApprovalAmount;
	}
}

