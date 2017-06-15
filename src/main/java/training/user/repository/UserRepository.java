package training.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import training.common.repository.BaseRepository;
import training.user.UserEntity;
import training.user.dto.UserDto;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, Integer>, UserRepositoryCustom {

	// @Query//("select u from UserEntity u where u.id = ?1")
	// query nu e necesar aici Spring data jpa stie sa ia numele metodei si sa-l
	// faca
	// exemplu neericit catre posibilitatea de a scrie query in interfata fara
	// sa necesite implementare

	public UserEntity findByUsername(String username);

	public UserEntity findById(Long id);

	@Query("select u from UserEntity u where u.username like ?1")
	public UserEntity findByUsernameLike(String username);

	// @Query("select u from UserEntity u where u.company.name = ?1")
	public List<UserEntity> findByCompanyName(String companyName);

}
