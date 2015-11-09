package gxc.rcalc.rest.repository.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import gxc.rcalc.rest.config.PersistenceConfig;
import gxc.rcalc.rest.entity.Company;
import gxc.rcalc.rest.repository.CompanyRepository;

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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		classes = {PersistenceConfig.class,JpaCompanyRepositoryTest.TestConfig.class}, 
		initializers = ConfigFileApplicationContextInitializer.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class JpaCompanyRepositoryTest {
	
	@Autowired
	private CompanyRepository repository;

	@Test
	public void findAllOk() {
		Collection<Company> companies = repository.findAll();
		assertNotNull(companies);
		assertEquals(4,companies.size());
		for(Company c : companies) {
			validateCompany(c);
		}
	}
	
	private void validateCompany(Company c) {
		assertNotNull(c);
		assertNotNull(c.getId());
		assertNotNull(c.getName());
		assertNotNull(c.getDescription());
	}
	
	@Test
	public void findByNameOk() {
		Collection<Company> commpanies = repository.findByName("medi"); // should match 'medicare' company
		assertNotNull(commpanies);
		assertEquals(1,commpanies.size());
		for(Company r : commpanies) {
			validateCompany(r);
		}
	}
	
	@Test
	public void findByIdOk() {
		Company company = repository.findById(2L);
		validateCompany(company);
		assertEquals("bombardier", company.getName());
	}
	
	@Test
	public void updateCompanyOk() {
		Company company = repository.findById(1L);
		validateCompany(company);
		assertEquals("aeros", company.getName());
		
		company.setDescription("this is a new description");
		Company updated = repository.createOrUpdate(company);
		validateCompany(updated);
		assertEquals("this is a new description", company.getDescription());
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
