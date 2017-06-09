package training.company.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import training.company.dto.CompanyDto;

@Repository
public interface CompanyRepositoryCustom {

	// per entity
	public List<CompanyDto> getAllCompanies();
}
