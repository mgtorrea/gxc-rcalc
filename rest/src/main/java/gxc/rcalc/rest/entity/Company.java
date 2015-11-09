package gxc.rcalc.rest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "companies")
public class Company implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "name", nullable = false, unique = true) 
	private String name;
	
	@Column(name = "url") 
	private String url;
	
	@Column(name = "description") 
	private String description;
	
	public Company() {
		
	}
	
	public Company(Long id, String name, String url) {
		this.id = id;
		this.name = name;
		this.url = url;
	}

	public Company(String name, String url) {
		this(null,name,url);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
