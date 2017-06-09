package training.company.dto;

import training.company.CompanyEntity;

public class CompanyDto {

	private Long id;
	private String name;

	public CompanyDto() {
	}

	public CompanyDto(CompanyEntity company){
		this.id = company.getId();
		this.name = company.getName();
	}

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


}
