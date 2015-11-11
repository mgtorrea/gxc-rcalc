package gxc.rcalc.rest.repository.jpa;

import gxc.rcalc.rest.entity.RiskIndex;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public interface JpaRiskIndexRepositoryDelegate extends CrudRepository<RiskIndex,Long> {
	
	@Transactional(readOnly = true)
	RiskIndex findById(Long id);
	
	@Transactional(readOnly = true)
	List<RiskIndex> findLatestByCompanyNameStartingWithIgnoreCase(String name);
	
	@Transactional(readOnly = true)
	List<RiskIndex> findAll();
}
