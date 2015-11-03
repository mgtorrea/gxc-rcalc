package gxc.rcalc.rest;

public class RiskIndexDto {
	
	/* Risk Index id */
	private Long id;
	
	/* Organization name */
	private String name;
	
	/* Risk index value */
	private Float riskIndex;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getRiskIndex() {
		return riskIndex;
	}

	public void setRiskIndex(Float riskIndex) {
		this.riskIndex = riskIndex;
	}
}
