package ru.aston.task3;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


public class PurchaseTest {
	PurchaseApprover departmentDirector;
	PurchaseApprover financialDirector;
	PurchaseApprover teamLeadApprover;

	@BeforeEach
	void setUp() {
		departmentDirector = new DepartmentDirector();
		financialDirector = new FinancialDirector();
		teamLeadApprover = new TeamLeadApprover();
	}

	@Test
	@DisplayName("Test default chain, cheap request")
	public void whenApproveLowCost_thenTeamLeadApproveExpected() {
		PurchaseRequest request = new PurchaseRequest("Clean the office", 350.32);
		String expectedApproveResult = "[TeamLead][Approved] Clean the office for %f".formatted(request.getAmount());

		PurchaseApprover approver = teamLeadApprover;
		teamLeadApprover.linkWith(departmentDirector).linkWith(financialDirector);
		Optional<String> approveResult = approver.approve(request);

		assertThat(approveResult.orElseThrow()).isEqualTo(expectedApproveResult);
	}

	@Test
	@DisplayName("Test default chain, default request")
	public void whenApproveDefaultCost_thenFinancialDirectorApproveExpected() {
		PurchaseRequest request = new PurchaseRequest("Buy a new car for", 25000.95);
		String expectedApproveResult = "[DepartmentDirector][Approved] Buy a new car for for %f".formatted(request.getAmount());

		PurchaseApprover approver = teamLeadApprover;
		teamLeadApprover.linkWith(departmentDirector).linkWith(financialDirector);
		Optional<String> approveResult = approver.approve(request);

		assertThat(approveResult.orElseThrow()).isEqualTo(expectedApproveResult);
	}

	@Test
	@DisplayName("Test default chain, expensive request")
	public void whenApproveExpensiveCost_thenFinancialDirectorApproveExpected() {
		PurchaseRequest request = new PurchaseRequest("Buy a new office", 657437893.52);
		String expectedApproveResult = "[FinancialDirector][Approved] Buy a new office for %f".formatted(request.getAmount());

		PurchaseApprover approver = teamLeadApprover;
		teamLeadApprover.linkWith(departmentDirector).linkWith(financialDirector);
		Optional<String> approveResult = approver.approve(request);

		assertThat(approveResult.orElseThrow()).isEqualTo(expectedApproveResult);
	}

	@Test
	@DisplayName("Test default chain, expensive request")
	public void whenApproveExpensiveCostWithoutFinancialDirector_thenRejectExpected() {
		PurchaseRequest request = new PurchaseRequest("Buy a new house", 657437893.52);

		PurchaseApprover approver = teamLeadApprover;
		teamLeadApprover.linkWith(departmentDirector);
		Optional<String> approveResult = approver.approve(request);

		assertThat(approveResult.isEmpty()).isTrue();
	}
}
