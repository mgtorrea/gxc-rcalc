package gxc.rcalc.rest.repository.jpa;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gxc.rcalc.rest.entity.Complain;
import gxc.rcalc.rest.repository.ComplainRepository;

@Component
public class JpaComplainRepository implements ComplainRepository {
	
	@Autowired
	private JpaComplainRepositoryDelegate delegate;

	@Override
	public Complain create(Complain complain) {
		return delegate.save(complain);
	}

	@Override
	public Collection<Complain> findByCompanyName(String name) {
		return delegate.findByCompanyNameStartingWithIgnoreCase(name);
	}

}
