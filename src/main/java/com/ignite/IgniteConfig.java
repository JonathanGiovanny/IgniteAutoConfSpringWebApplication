package com.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.store.jdbc.dialect.H2Dialect;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.springdata.repository.config.EnableIgniteRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.ignite.model.Student;
import com.ignite.persistence.H2DataSourceFactory;
import com.ignite.utilities.IgniteAutoConfig;

@Component
@EnableIgniteRepositories
public class IgniteConfig {

	@Autowired
	private Ignite igniteInstance;
	
	@Bean
	public Ignite igniteInstance() {
		try {
			IgniteConfiguration igniteConfiguration = new IgniteConfiguration();
			igniteConfiguration.setClientMode(false);

			IgniteAutoConfig.addClass(Student.class);

			// DS for the schema where I have the data
			H2DataSourceFactory dsFactory = H2DataSourceFactory.getInstance();

			// Generate the cacheConfiguration for the classes added before
			igniteConfiguration.setCacheConfiguration(IgniteAutoConfig.generateCacheConfiguration(dsFactory, new H2Dialect()));

			Ignite ignite = Ignition.start(igniteConfiguration);
			System.out.println("[IgniteServerNode] Node started");
			IgniteCache<Long, Student> cache = ignite.getOrCreateCache("StudentCache");
			cache.loadCache(null);

			return ignite;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Bean
	public IgniteCache<?, ?> igniteCache() {
		return igniteInstance.getOrCreateCache(IgniteAutoConfig.getCacheNames().get(0));
	}
}