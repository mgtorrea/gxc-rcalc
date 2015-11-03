package gxc.rcalc.rest.repository;

import gxc.rcalc.rest.entity.RiskIndex;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public interface JpaRiskIndexRepositoryDelegate extends CrudRepository<RiskIndex,Long> {
	
	@Transactional(readOnly = true)
	RiskIndex findById(Long id);
	
	// FIXME: add selection for latest only of each company
	//@Query("select r from RiskIndex r where r.company like CONCAT(?1, '%')")
	@Transactional(readOnly = true)
	List<RiskIndex> findLatestByCompanyNameStartingWith(String name);
	
	// FIXME: add selection for latest only of each company
	@Transactional(readOnly = true)
	List<RiskIndex> findAll();
}
