package training.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import training.common.entity.ExternalUUIDEntity;
import training.company.CompanyEntity;

@Entity
@Table(name = "USER")
public class UserEntity extends ExternalUUIDEntity {
	// @org.hibernate.annotations.Entity(dynamicUpdate = true) -> pentru update
	// doar ce este modificat
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "USERNAME")
	private String username;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMPANYFK", referencedColumnName = "ID")
	public CompanyEntity company;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public CompanyEntity getCompany() {
		return company;
	}

	public void setCompany(CompanyEntity company) {
		this.company = company;
	}



}
