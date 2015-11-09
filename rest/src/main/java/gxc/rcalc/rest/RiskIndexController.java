package gxc.rcalc.rest;

import gxc.rcalc.rest.entity.RiskIndex;
import gxc.rcalc.rest.repository.RiskIndexRepository;
import gxc.rcalc.rest.util.TextUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * RiskIndex Rest Controller (public endpoints)
 * 
 * @author fcisneros
 * 
 */
@RestController
@RequestMapping("/index")
public class RiskIndexController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RiskIndexController.class);
	
	@Autowired
	RiskIndexRepository repository;

	@RequestMapping(method = RequestMethod.GET)
	public Collection<RiskIndexDto> getAll() {
		try {
			return dto(repository.findAll());
		} catch(Throwable t) {
			LOGGER.error("Could not get all RiskIndex values",t);
			return Collections.emptyList();
		}
	}
	
	@RequestMapping(value = "/{name}",method = RequestMethod.GET)
	public Collection<RiskIndexDto> getByName(@PathVariable String name) {
		if(StringUtils.isEmpty(name)) {
			LOGGER.warn("Invalid company name: {}", name);
			return Collections.emptyList();
		}
		
		final String normalized = TextUtils.normalize(name);
		
		return dto(repository.findByCompanyName(normalized));
	}
	
	private static RiskIndexDto dto(RiskIndex ri) {
		RiskIndexDto dto = new RiskIndexDto();
		dto.setId(ri.getId());
		dto.setName(coalesce(ri.getCompany().getName(), ri.getCompany().getUrl()));
		dto.setRiskIndex(ri.getRisk());
		
		return dto;
	}
	
	private static Collection<RiskIndexDto> dto(Collection<RiskIndex> risks) {
		List<RiskIndexDto> list = new ArrayList<RiskIndexDto>();
		for(RiskIndex ri : risks ) {
			list.add(dto(ri));
		}
		
		return list;
	}
	
	private static String coalesce(String... str) {
		for(String s : str ) {
			if(s != null) {
				return s;
			}
		}
		
		return null;
	}

}
