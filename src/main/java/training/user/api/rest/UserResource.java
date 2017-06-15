package training.user.api.rest;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import training.common.annotation.RestSubResource;
import training.company.api.rest.CompanyResource;
import training.user.dto.UserDto;
import training.user.service.UserService;

@RestSubResource(parent = CompanyResource.class)
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class UserResource {

	@Autowired
	private UserService userService;

	@GET
	public List<UserDto> getCompanyUsers(@PathParam("name") String companyName) {
		return userService.getCompanyUsers(companyName);
	}

	@Path("/{username}")
	public UserDto getUserByName(@PathParam("username") String username){
		return userService.getUserByName(username);
	}

	@PUT
	public void saveOrUpdate(@RequestBody UserDto user) {
		userService.saveOrUpdateUser(user);
	}
	@DELETE
	public void delete(@RequestBody UserDto user) {
		userService.deleteUser(user.getId());
	}
}
