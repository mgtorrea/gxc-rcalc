package gxc.rcalc.rest;

import gxc.rcalc.rest.entity.Company;
import gxc.rcalc.rest.entity.Complain;
import gxc.rcalc.rest.entity.RiskIndex;
import gxc.rcalc.rest.repository.CompanyRepository;
import gxc.rcalc.rest.repository.ComplainRepository;
import gxc.rcalc.rest.repository.RiskIndexRepository;
import gxc.rcalc.rest.util.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Admin endpoints Rest Controller
 * 
 * @author fcisneros
 * 
 */
// TODO: add security for this controller
@RestController
@RequestMapping("/admin")
public class AdminController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private CompanyRepository companies;
	
	@Autowired
	private ComplainRepository complains;
	
	@Autowired
	private RiskIndexRepository risks;
	
	@RequestMapping(value = "/index", method = RequestMethod.PUT)
	public RiskIndexDto addRiskIndex(@RequestBody RiskIndexDto ri) {
		Company company = findOrCreateCompany(ri.getCompanyId(), ri.getCompanyName(), null);
		if(company == null) {
			LOGGER.error("Could not create RiskIndex without a Company name or id");
			return null; // TODO: add error object
		}
		
		RiskIndex entity = new RiskIndex(
				ri.getRiskIndex(), 
				new Date(), 
				company);
				
		return RiskIndexController.dto(risks.create(entity)); // FIXME: take dto() method out to a utils class
	}
	
	@RequestMapping(value = "/complains/{name}", method = RequestMethod.GET)
	public List<ComplainDto> findByCompany(@PathVariable String name) {
		
		Company company = findMostSimilarByName(name);
		if(company == null) {
			LOGGER.error("Could not find Complains with the given companyName: {}", name);
			return Collections.emptyList();
		}
		
		List<ComplainDto> result = new ArrayList<ComplainDto>();
		for(Complain complain : complains.findByCompanyName(name) ) {
			result.add(dto(complain));
		}
			
		return result;
	}
	
	@RequestMapping(value = "/complains", method = RequestMethod.PUT)
	public ComplainDto addComplain(@RequestBody ComplainDto complain) {
		Company company = 
				findOrCreateCompany(complain.getCompanyId(), complain.getCompanyName(), complain.getCompanyUrl());
		if(company == null) {
			LOGGER.error("Could not create Complain without a Company name or id");
			return null; // TODO: add error object
		}
		
		Complain entity = new Complain(
				complain.getRating(), 
				new Date(), 
				TextUtils.normalize(complain.getComments()),
				company);
				
		return dto(complains.create(entity));
	}
	
	private ComplainDto dto(Complain c) {
		if(c == null) {
			return null; // TODO: add error object
		}
		
		ComplainDto dto = new ComplainDto();
		dto.setCompanyId(c.getCompany().getId());
		dto.setCompanyName(c.getCompany().getName());
		dto.setComments(c.getComments());
		dto.setRating(c.getRating());
		
		return dto;
	}
	
	/*
	 * Returns the Company found by id or the most similar by name
	 */
	private Company findOrCreateCompany(Long id, String companyName, String url) {
		String name = TextUtils.normalize(companyName);
		if(id != null && id > 0) {
			return companies.findById(id);
		} else if(StringUtils.hasText(name)) {
			
			Company current = findMostSimilarByName(name);
			
			/* is new company */
			if(current == null) {
				current = new Company(name, url); // TODO: Add url detection/validation
				current = companies.createOrUpdate(current);
			}
			
			return current;
		}
		
		return null;
	}
	
	/*
	 * Finds the most similar company by Name (using levenshtein distance)
	 */
	private Company findMostSimilarByName(String name) {
		final String companyName = TextUtils.normalize(name);
		int shortest = Integer.MAX_VALUE;
		Company company = null;
		for(Company c : companies.findByName(companyName)) {
			int distance = TextUtils.levenshtein(companyName, c.getName());
			if(distance < shortest) {
				shortest = distance;
				company = c;
			}
		}
		
		return company;
	}

}
