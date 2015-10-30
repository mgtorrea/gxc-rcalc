package gxc.rcalc.rest.repository;

import gxc.rcalc.rest.entity.RiskIndex;

import java.util.Collection;

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
		return delegate.findLatestByCompanyNameStartingWith(companyName);
	}

	@Override
	public Collection<RiskIndex> findAll() {
		return delegate.findAll();
	}

}
