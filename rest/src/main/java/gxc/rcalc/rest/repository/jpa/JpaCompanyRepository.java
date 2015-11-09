package gxc.rcalc.rest.repository.jpa;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gxc.rcalc.rest.entity.Company;
import gxc.rcalc.rest.repository.CompanyRepository;

// TODO: add cache to avoid hitting DB every time
@Component
public class JpaCompanyRepository implements CompanyRepository {
	
	@Autowired
	private JpaCompanyRepositoryDelegate delegate;

	@Override
	public Company createOrUpdate(Company company) {
		return delegate.save(company);
	}

	@Override
	public Collection<Company> findAll() {
		return delegate.findAll();
	}

	@Override
	public Company findById(Long id) {
		return delegate.findById(id);
	}

	@Override
	public Collection<Company> findByName(String name) {
		return delegate.findByNameStartingWith(name);
	}

}
