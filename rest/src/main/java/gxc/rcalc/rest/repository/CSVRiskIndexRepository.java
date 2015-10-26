package gxc.rcalc.rest.repository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Dummy RiskIndexRepository implementation used for dev and tests
 * 
 * @author fcisneros
 * 
 */
@Component
public class CSVRiskIndexRepository implements RiskIndexRepository, InitializingBean {
	
	@Value("${index.resource}")
	private Resource resource;
	
	private List <RiskIndex> cache;

	@Override
	public void afterPropertiesSet() throws Exception {
		cache = new ArrayList<RiskIndex>();
		
		 InputStream is = resource.getInputStream();
         BufferedReader br = new BufferedReader(new InputStreamReader(is));
       	
         String line;
         while ((line = br.readLine()) != null) {
        	String[] parts = StringUtils.tokenizeToStringArray(line, ",");
 			
 			RiskIndex ri = new RiskIndex();
 			ri.setId(Long.valueOf(parts[0]));
 			ri.setCompanyName(parts[1]);
 			ri.setCompanyURL(extractURL(parts[2]));
 			ri.setRisk(Integer.valueOf(parts[3]));
 			
 			cache.add(ri);
      	  } 
         br.close();
	}
	
	private URL extractURL(String url) throws MalformedURLException {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		final String fixed = (url.startsWith("http")) ? url : "http://" + url;
		
		return new URL(fixed);
	}

	@Override
	public RiskIndex createOrUpdate(RiskIndex index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public RiskIndex findById(Integer id) {
		for(RiskIndex ri : cache) {
			if(ri.getId().equals(id)) {
				return ri;
			}
		}
		
		return null;
	}

	@Override
	public Collection<RiskIndex> findByCompanyName(String companyName) {
		List<RiskIndex> risks = new ArrayList<RiskIndex>();
		for(RiskIndex ri : cache) {
			if(ri.getCompanyName().startsWith(companyName) 
				|| ri.getCompanyName().contains(companyName)) {
				
				risks.add(ri);
			}
		}
		
		return risks;
	}

	@Override
	public Collection<RiskIndex> findAll() {
		return new ArrayList<RiskIndex>(cache);
	}

}
