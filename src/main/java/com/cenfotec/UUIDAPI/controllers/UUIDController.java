package com.cenfotec.UUIDAPI.controllers;

import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UUIDController {
	
	@GetMapping("/create")
	public ResponseEntity<UUID> getUUID(){
		return ResponseEntity.ok(UUID.randomUUID());
	}
}
