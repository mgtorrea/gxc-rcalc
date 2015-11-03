package gxc.rcalc.rest.repository;

import gxc.rcalc.rest.entity.RiskIndex;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JpaRiskIndexRepository implements RiskIndexRepository {
	
	@Autowired
	private JpaRiskIndexRepositoryDelegate delegate;
	
	@Override
	public RiskIndex createOrUpdate(RiskIndex index) {
		// TODO: implement if needed
		throw new UnsupportedOperationException();
	}

	@Override
	public RiskIndex findById(Long id) {
		return delegate.findById(id);
	}

	@Override
	public Collection<RiskIndex> findByCompanyName(String companyName) {
		return latestByCompany(delegate.findLatestByCompanyNameStartingWith(companyName));
	}

	@Override
	public Collection<RiskIndex> findAll() {
		return latestByCompany(delegate.findAll());
	}
	
	/* Filters out only latest riskIndex entity by each company. 
	 * ugly but easier to implement than in jpa query 
	 */
	private Collection<RiskIndex> latestByCompany(Collection<RiskIndex> indexes) {
		Map<Long,RiskIndex> map = new HashMap<Long,RiskIndex>();
		for(RiskIndex r : indexes) {
			Long company = r.getCompany().getId();
			RiskIndex ri = map.get(company);
			if(ri == null) {
				map.put(company, r);
			} else {
				if(r.getCreatedAt().compareTo(ri.getCreatedAt()) >= 0) {
					map.put(company, r);
				}
			}
		}
		
		return map.values();
	}

}
