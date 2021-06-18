package com.order.app;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.order.app.request.PaymentDetailsRequest;
import com.order.app.response.PaymentDeductionResponseDTO;

@FeignClient(name = "bank-payment-app/bank-app/payment")
public interface IPaymentInterface {

	@GetMapping("/paymentDeduction")
	public ResponseEntity<PaymentDeductionResponseDTO> paymentDeduction(
			@RequestBody PaymentDetailsRequest paymentDetailsRequest);

}
