package com.ignite;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.store.jdbc.dialect.H2Dialect;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.springdata20.repository.config.EnableIgniteRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.ignite.model.Signature;
import com.ignite.model.Student;
import com.ignite.persistence.H2DataSourceFactory;
import com.ignite.utilities.IgniteAutoConfig;

@Component
@EnableIgniteRepositories("com.ignite.repository")
public class IgniteConfig {

	@Bean
	public Ignite igniteInstance() {
		try {
			IgniteConfiguration igniteConfiguration = new IgniteConfiguration();
			igniteConfiguration.setClientMode(false);

			IgniteAutoConfig.addClass(Student.class);
			IgniteAutoConfig.addClass(Signature.class);

			// DS for the schema where I have the data
			H2DataSourceFactory dsFactory = H2DataSourceFactory.getInstance();

			// Generate the cacheConfiguration for the classes added before
			Map<String, CacheConfiguration<?, ?>> cachesConfigData = IgniteAutoConfig.generateCacheConfiguration(dsFactory, new H2Dialect());
			// Modify specific cache settings
//			cachesConfigData.get("SignatureCache").setReadThrough(false);

			Collection<CacheConfiguration<?, ?>> cachesConfig = cachesConfigData.values();

			igniteConfiguration.setCacheConfiguration(cachesConfig.toArray(new CacheConfiguration<?, ?>[cachesConfig.size()]));

			Ignite ignite = Ignition.start(igniteConfiguration);

			System.out.println("[IgniteServerNode] Node started");

			// Start all the caches
			List<String> cacheNames = IgniteAutoConfig.getCacheNames();
			for (String cacheName : cacheNames) {
				IgniteCache<?, ?> cache = ignite.getOrCreateCache(cacheName);
				
				// Load the cache
				cache.loadCache(null);
			}

			return ignite;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}