package training.company;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import training.common.entity.ExternalUUIDEntity;
import training.user.UserEntity;

@Entity
@Table(name = "COMPANY")
public class CompanyEntity extends ExternalUUIDEntity {
	// @org.hibernate.annotations.Entity(dynamicUpdate = true) -> pentru update
	// doar ce este modificat
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

/*	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
	private Set<UserEntity> userSet;*/

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

	/*
	 * public Set<UserEntity> getUserSet() { return userSet; }
	 *
	 * public void setUserSet(Set<UserEntity> userSet) { this.userSet = userSet;
	 * }
	 */

}