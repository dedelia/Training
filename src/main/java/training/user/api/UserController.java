/*package training.user.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import training.user.dto.UserDto;
import training.user.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users/all", method = RequestMethod.GET)
	public List<UserDto> getUserSet() {
		//List<UserDto> userSet = userService.getUserSet();

		//using method from general baseRespo adapted in service
		return userService.getAll();
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	@ResponseBody
	public void addUser(@RequestBody UserDto user) {
		userService.addUser(user);
	}

	@RequestMapping(value = "/users", method = RequestMethod.PUT)
	public void updateUser(@RequestBody UserDto user) {
		// finds user by id and updates the username, if there's no id in the
		// obj-> should not update
		userService.updateUser(user);

	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}

	@RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
	public UserDto getUserByUsername(@PathVariable String  username) {
		//List<UserDto> userSet = userService.getUserSet();

		//using method from general baseRespo adapted in service
		return userService.getUserByName(username);
	}
}
*/