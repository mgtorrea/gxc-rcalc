package gxc.rcalc.rest.repository;

import java.util.Collection;

public interface RiskIndexRepository {
	
	RiskIndex createOrUpdate(RiskIndex index);
	
	RiskIndex findById(Integer id);
	
	Collection<RiskIndex> findByCompanyName(String companyName);

	Collection<RiskIndex> findAll();
}
