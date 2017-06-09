package training.common.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import training.user.dto.UserDto;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

	/**
	 * Finds an object by String UUID without dashes
	 *
	 */
	public T findByExternalUUID(String externalUUIDWithoutDashes);

	public List<T> findAllObjects();

}
