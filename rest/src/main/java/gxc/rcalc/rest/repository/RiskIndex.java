package gxc.rcalc.rest.repository;

import java.net.URL;

public class RiskIndex {
	
	private Long id;
	
	private String companyName;
	
	private URL companyURL;
	
	private Integer risk;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public URL getCompanyURL() {
		return companyURL;
	}

	public void setCompanyURL(URL companyURL) {
		this.companyURL = companyURL;
	}

	public Integer getRisk() {
		return risk;
	}

	public void setRisk(Integer risk) {
		this.risk = risk;
	}
}
