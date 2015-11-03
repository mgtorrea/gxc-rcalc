package gxc.rcalc.rest.repository.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import gxc.rcalc.rest.config.PersistenceConfig;
import gxc.rcalc.rest.entity.RiskIndex;
import gxc.rcalc.rest.repository.RiskIndexRepository;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		classes = {PersistenceConfig.class,JpaRiskIndexRepositoryTest.TestConfig.class}, 
		initializers = ConfigFileApplicationContextInitializer.class)
public class JpaRiskIndexRepositoryTest {
	
	@Autowired
	private RiskIndexRepository repository;

	@Test
	public void findAllOk() {
		Collection<RiskIndex> indexes = repository.findAll();
		assertNotNull(indexes);
		assertEquals(6,indexes.size());
	}
	
	@Configuration
	public static class TestConfig {
		
		@Bean
		public static PropertySourcesPlaceholderConfigurer properties() {
			PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
			Resource[] resources = new ClassPathResource[] { new ClassPathResource(
					"application-test.yml") };
			pspc.setLocations(resources);
			pspc.setIgnoreUnresolvablePlaceholders(false);
			return pspc;
		}
	}
}
