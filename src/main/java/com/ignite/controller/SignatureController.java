package com.ignite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ignite.model.Signature;
import com.ignite.services.SignatureService;

@RestController
@RequestMapping("/signature")
public class SignatureController {

	@Autowired
	private SignatureService signatureService;

	@GetMapping("/getAll")
	public List<Signature> getAll() {
		return signatureService.getAll();
	}

	@GetMapping("/getOne/{id}")
	public Signature getOne(@PathVariable Long id) {
		return signatureService.getOne(id);
	}
}
