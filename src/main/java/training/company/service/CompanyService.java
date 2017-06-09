package training.company.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import training.company.CompanyEntity;
import training.company.api.rest.CompanyResource;
import training.company.dto.CompanyDto;
import training.company.repository.CompanyRepository;
import training.user.UserEntity;
import training.user.dto.UserDto;
import training.user.repository.UserRepository;

@Service
@Transactional
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	public List<CompanyDto> getAll() {

		List<CompanyDto> companies = new ArrayList<CompanyDto>();
		for (CompanyEntity tempCompany : companyRepository.findAllObjects()) {
			companies.add(new CompanyDto(tempCompany));
		}
		return companies;
	}

	public void addCompany(CompanyDto company) {
		// new entity ->user name
		CompanyEntity tempCompany = new CompanyEntity();
		tempCompany.setName(company.getName());
		companyRepository.save(tempCompany);
	}

	public CompanyDto getCompany(Long id) {
		// TODO Auto-generated method stub
		return new CompanyDto(companyRepository.findById(id));
	}

}
