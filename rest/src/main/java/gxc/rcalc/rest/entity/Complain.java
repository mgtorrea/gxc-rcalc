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
@Table(name = "complains")
public class Complain implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "rating", nullable = false)
	private Integer rating;
	
	@Column(name = "created_at", nullable = false)
	private Date createdAt;
	
	@Column(name = "comments")
	private String comments;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "company_id")
	private Company company;
	
	public Complain() {
		
	}
	
	public Complain(Long id, Integer rating, Date createdAt, String comments, Company company) {
		this.id = id;
		this.rating = rating;
		this.createdAt = createdAt;
		this.comments = comments;
		this.company = company;
	}

	public Complain(Integer rating, Date createdAt, String comments, Company company) {
		this(null,rating,createdAt,comments, company);
	}
	
	public Complain(Integer rating, Date createdAt, Company company) {
		this(null,rating,createdAt,"",company);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer risk) {
		this.rating = risk;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Company getCompany() {
		return company;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	// TODO: correctly implements equals and hashcode
}
