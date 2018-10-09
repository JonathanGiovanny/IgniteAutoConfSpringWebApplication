package com.ignite.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignite.model.Signature;
import com.ignite.repository.SignatureRepository;

@Service
public class SignatureServiceImpl implements SignatureService {

	@Autowired
	private SignatureRepository signatureRepo;

	@Override
	public List<Signature> getAll() {
		List<Signature> signatures = new ArrayList<>();
		System.out.println("Total signatures: " + count());
		signatures = signatureRepo.getAll();
		return signatures;
	}

	@Override
	public Signature getOne(Long id) {
		return signatureRepo.findById(id).get();
	}

	private long count() {
		return signatureRepo.count();
	}
}
