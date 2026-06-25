package ru.aston.task3;


import lombok.Getter;
import org.jetbrains.annotations.NotNull;


public class PurchaseRequest {
	@Getter
	private final String purpose;
	@Getter
	private final double amount;

	PurchaseRequest(final @NotNull String purpose, final double amount) {
		this.purpose = purpose;
		this.amount = amount;
	}
}
