package training.company.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import training.common.repository.BaseRepository;
import training.company.CompanyEntity;
import training.user.UserEntity;

@Repository
public interface CompanyRepository extends BaseRepository<CompanyEntity, Integer>, CompanyRepositoryCustom {

	@Query("select c from CompanyEntity c where c.id = ?1")
	public CompanyEntity findById(Long id);

	@Query("select c from CompanyEntity c where c.name like ?1")
	public CompanyEntity findByUsernameLike(String name);
}
