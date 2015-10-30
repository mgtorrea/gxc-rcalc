package gxc.rcalc.rest.repository;

import gxc.rcalc.rest.entity.RiskIndex;

import java.util.Collection;

public interface RiskIndexRepository {
	
	RiskIndex createOrUpdate(RiskIndex index);
	
	RiskIndex findById(Long id);
	
	Collection<RiskIndex> findByCompanyName(String companyName);

	Collection<RiskIndex> findAll();
}
