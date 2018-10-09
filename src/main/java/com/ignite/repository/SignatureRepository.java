package com.ignite.repository;

import java.util.List;

import org.apache.ignite.springdata20.repository.IgniteRepository;
import org.apache.ignite.springdata20.repository.config.Query;
import org.apache.ignite.springdata20.repository.config.RepositoryConfig;

import com.ignite.model.Signature;

@RepositoryConfig(cacheName = "SignatureCache")
public interface SignatureRepository extends IgniteRepository<Signature, Long> {

	@Query("SELECT * FROM SIGNATURE ORDER BY 1")
	public List<Signature> getAll();

	@Query("SELECT COUNT(0) FROM SIGNATURE")
	public long count();
}
