package training.company.api.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import training.common.annotation.RestResource;
import training.company.dto.CompanyDto;
import training.company.service.CompanyNotificationService;
import training.company.service.CompanyService;
import training.user.api.rest.UserResource;

@RestResource
@Path("/companies")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class CompanyResource {

	@Autowired
	private CompanyService companyService;

	@Autowired
	CompanyNotificationService companyNotificationService;

	@Autowired
	UserResource userResource;

	@GET
	public List<CompanyDto> getCompanySet() {
		return companyService.getAll();
	}

	@Path("/{id}")
	public CompanyDto getCompanyById(@PathParam("id") Long id) {
		return companyService.getCompany(id);
	}

	@Path("/{name}/users")
	public UserResource getCompanyUsers() {
		return userResource;
	}

}
