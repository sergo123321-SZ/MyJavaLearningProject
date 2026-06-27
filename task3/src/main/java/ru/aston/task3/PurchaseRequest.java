package ru.aston.task3;


import lombok.Getter;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;


public class PurchaseRequest {
	@Getter
	private final String purpose;
	@Getter
	private final double amount;

	PurchaseRequest(final @NotNull String purpose, final double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Amount MUST be > 0");
		}
		this.purpose = Objects.requireNonNull(purpose, "Purpose MUST be provided");
		this.amount = amount;
	}
}
