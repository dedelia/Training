package training.user.dto;

import training.company.CompanyEntity;
import training.user.UserEntity;

public class UserDto {

	private Long id;
	private String username;
	private CompanyEntity company;

    public UserDto() {
    }

	public UserDto(UserEntity user) {

    	this.id=user.getId();
        this.username = user.getUsername();
        this.company=user.company;
    }


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
