package com.email.controller;

import com.email.model.EmailRequest;
import com.email.model.EmailResponse;
import com.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmailController {

	@Autowired
	EmailService emailService;
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome()
	{
		return "Hello this is an API ";
	}

	//API to send mail
	@RequestMapping(value = "/sendmail", method = RequestMethod.POST)
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request){
		//return  this.emailService.sendEmail();
		System.out.println(request);
		boolean result=this.emailService.sendEmail(request.getSubject(),request.getMessage(),request.getTo());
		if(result){
			return ResponseEntity.ok(new EmailResponse("Email Send Successfully"));
		}
		return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email not sent successfully"));

	}


}