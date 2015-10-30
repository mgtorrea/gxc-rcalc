package gxc.rcalc.rest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "risk_index")
public class RiskIndex implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "risk", nullable = false)
	private Integer risk;
	
	@Column(name = "created_at", nullable = false)
	private Date createdAt;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "company_id")
	private Company company;
	
	public RiskIndex(Long id, Integer risk, Date createdAt, Company company) {
		this.id = id;
		this.risk = risk;
		this.createdAt = createdAt;
		this.company = company;
	}

	public RiskIndex(Integer risk, Date createdAt, Company company) {
		this(null,risk,createdAt,company);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRisk() {
		return risk;
	}

	public void setRisk(Integer risk) {
		this.risk = risk;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Company getCompany() {
		return company;
	}
}
