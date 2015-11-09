package gxc.rcalc.rest.repository.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import gxc.rcalc.rest.config.PersistenceConfig;
import gxc.rcalc.rest.entity.Company;
import gxc.rcalc.rest.entity.Complain;
import gxc.rcalc.rest.repository.ComplainRepository;

import java.util.Date;

import org.junit.Assert;
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
		classes = {PersistenceConfig.class,JpaComplainRepositoryTest.TestConfig.class}, 
		initializers = ConfigFileApplicationContextInitializer.class)
public class JpaComplainRepositoryTest {
	
	@Autowired
	private ComplainRepository repository;

	@Test
	public void createComplainOk() {
		Complain c = createComplain();
		Complain created = repository.create(c);
		assertNotNull(created);
		assertNotNull(created.getId());
		assertEquals(Integer.valueOf(2),created.getRating());
		assertNotNull(created.getCompany());
	}
	
	@Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
	public void createComplainNull() {
		repository.create(null);
		Assert.fail("should have thrown exception when trying to create Complain with null object");
	}
	
	private Complain createComplain() {
		Company company = new Company(1L,null,null);
		Complain c = new Complain(2, new Date(), "bad service", company);
		
		return c;
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
