package training.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import training.common.annotation.TransactionalService;
import training.company.CompanyEntity;
import training.user.UserEntity;
import training.user.dto.UserDto;
import training.user.repository.UserRepository;

@TransactionalService
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserNotificationService				userNotificationService;

	public List<UserDto> getUserSet() {
		return userRepository.getAllUsersCriteria();
	}

	public UserDto getUserByName(String username) {
		UserDto user = null;
		if (userRepository.findByUsername(username) != null)
		{
			user = new UserDto(userRepository.findByUsername(username));

		//rabbit first test.. uuu
		userNotificationService.sendSuPoNotification(userRepository.findByUsername(username));
		}

		return user;
	}

	public List<UserDto> getAll() {

		List<UserDto> listUser = new ArrayList<UserDto>();
		for (UserEntity tempUser : userRepository.findAll()) {
			listUser.add(new UserDto(tempUser));
		}
		return listUser;
	}

	public void saveOrUpdateUser(UserDto user) {
		UserEntity newUser = userRepository.findById(user.getId());
		// se face automat
		if (newUser != null)
			newUser.setUsername(user.getUsername());
		else {
			newUser = new UserEntity();
			newUser.setUsername(user.getUsername());
			newUser.setCompany(user.getCompany());
			userRepository.save(newUser);
		}

	}

	public void deleteUser(Long id) {
		UserEntity user = userRepository.findById(id);
		if (user != null)
			userRepository.delete(user);

	}

	public List<UserDto> getCompanyUsers(String companyName) {
		List<UserDto> listUser = new ArrayList<UserDto>();
		for (UserEntity tempUser : userRepository.findByCompanyName(companyName)) {
			listUser.add(new UserDto(tempUser));
		}
		return listUser;
	}

}
