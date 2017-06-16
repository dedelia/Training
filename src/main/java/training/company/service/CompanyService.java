package training.company.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import training.common.annotation.TransactionalService;
import training.company.CompanyEntity;
import training.company.dto.CompanyDto;
import training.company.repository.CompanyRepository;

@TransactionalService
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	CompanyNotificationService companyNotificationService;

	public List<CompanyDto> getAll() {

		List<CompanyDto> companies = new ArrayList<CompanyDto>();
		for (CompanyEntity tempCompany : companyRepository.findAllObjects()) {
			companies.add(new CompanyDto(tempCompany));
			companyNotificationService.sendCompanyNotification(tempCompany);
		}
		// could also wend a whole list
		// companyNotificationService.sendNotification(companies);


		return companies;
	}

	public void addCompany(CompanyDto company) {
		// new entity ->user name
		CompanyEntity tempCompany = new CompanyEntity();
		tempCompany.setName(company.getName());
		companyRepository.save(tempCompany);
	}

	public CompanyDto getCompany(Long id) {
		CompanyDto company = null;
		if (companyRepository.findById(id) != null) {
			company = new CompanyDto(companyRepository.findById(id));
		}

		return company;
	}

}
