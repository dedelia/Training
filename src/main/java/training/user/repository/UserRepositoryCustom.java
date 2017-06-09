package training.user.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import training.user.UserEntity;
import training.user.dto.UserDto;

@Repository
public interface UserRepositoryCustom {
	//per entity
	public List<UserDto> getAllUsers();

	public List<UserDto> getAllUsersCriteria();

}
