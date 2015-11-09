package gxc.rcalc.rest.repository;

import java.util.Collection;

import gxc.rcalc.rest.entity.Complain;

public interface ComplainRepository {
	
	Complain create(Complain complain);
	
	Collection<Complain> findByCompanyName(String name);

}
