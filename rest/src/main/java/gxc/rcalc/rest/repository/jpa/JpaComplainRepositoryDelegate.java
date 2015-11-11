package gxc.rcalc.rest.repository.jpa;

import java.util.List;

import gxc.rcalc.rest.entity.Complain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public interface JpaComplainRepositoryDelegate extends CrudRepository<Complain,Long> {
	
	@Transactional(readOnly = true)
	List<Complain> findByCompanyNameStartingWithIgnoreCase(String name);
	
}
