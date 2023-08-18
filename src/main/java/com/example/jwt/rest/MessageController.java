package com.example.jwt.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

	@GetMapping("/message")
	public String getMessage() {
		return "User authenticated successfully, This api is now accessible";
	}

}
