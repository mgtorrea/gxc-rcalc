package gxc.rcalc.rest;

import java.util.Date;

public class RiskIndexDto {
	
	private Long companyId;
	
	private String companyName;
	
	private Float riskIndex;
	
	private Date date;

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long id) {
		this.companyId = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String name) {
		this.companyName = name;
	}

	public Float getRiskIndex() {
		return riskIndex;
	}

	public void setRiskIndex(Float riskIndex) {
		this.riskIndex = riskIndex;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
