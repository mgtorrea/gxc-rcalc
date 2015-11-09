package gxc.rcalc.rest.repository;

import java.util.Collection;

import gxc.rcalc.rest.entity.Company;

public interface CompanyRepository {
	
	Company createOrUpdate(Company company);
	
	Collection<Company> findAll();
	
	Company findById(Long id);
	
	Collection<Company> findByName(String name);

}
