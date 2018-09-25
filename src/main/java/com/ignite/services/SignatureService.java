package com.ignite.services;

import java.util.List;

import com.ignite.model.Signature;

public interface SignatureService {

	public List<Signature> getAll();
	public Signature getOne(Long id);
}
