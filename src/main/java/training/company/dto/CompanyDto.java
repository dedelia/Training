package training.company.dto;

import java.util.UUID;

import training.company.CompanyEntity;

public class CompanyDto {

	private Long id;
	private String name;
	private UUID UUID;

	public CompanyDto() {
	}

	public CompanyDto(CompanyEntity company){
		this.id = company.getId();
		this.name = company.getName();
		this.UUID=company.getExternalUUID();
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

	public UUID getUUID() {
		return UUID;
	}

	public void setUUID(UUID UUID) {
		UUID = UUID;
	}



}
