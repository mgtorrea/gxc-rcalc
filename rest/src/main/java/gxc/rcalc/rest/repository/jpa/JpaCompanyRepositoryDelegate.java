package gxc.rcalc.rest.repository.jpa;

import gxc.rcalc.rest.entity.Company;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public interface JpaCompanyRepositoryDelegate extends CrudRepository<Company,Long> {
	
	@Transactional(readOnly = true)
	Company findById(Long id);
	
	@Transactional(readOnly = true)
	List<Company> findByNameStartingWith(String name);
	
	@Transactional(readOnly = true)
	List<Company> findAll();
	
}
