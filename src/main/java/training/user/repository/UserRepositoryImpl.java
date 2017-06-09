package training.user.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import training.user.UserEntity;
import training.user.dto.UserDto;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom{

	@Autowired
	EntityManager entityManager;

	// remember, din ce am vazut din repo iese si intra dto !!!!
	// implementeaza custom, care implementeaza si standard => i've got it all
	// :D

	@Override
	public List<UserDto> getAllUsers() {

		StringBuilder sb = new StringBuilder();
		// select dto obj from entity , can do specific asociations

		sb.append(" select new training.user.dto.UserDto(u) from UserEntity u");
		Query query = entityManager.createQuery(sb.toString());
		sb = new StringBuilder();
		sb.append(" select u.username as username, u.id as id from UserEntity u");
		return entityManager.createQuery(sb.toString()).unwrap(org.hibernate.Query.class)
				.setResultTransformer(Transformers.aliasToBean(UserDto.class)).list();

	}

	public List<UserDto> getAllUsersCriteria() {
		CriteriaQuery<UserEntity> criteria = entityManager.getCriteriaBuilder().createQuery(UserEntity.class);
		Root<UserEntity> user = criteria.from(UserEntity.class);
		criteria.select(user);
		List<UserDto> setUser = new ArrayList<UserDto>();
		for (UserEntity tempUser : entityManager.createQuery(criteria).getResultList()) {
			setUser.add(new UserDto(tempUser));
		}
		return setUser;
	}

	//@Transactional
/*	public void addUser(UserDto user) {
		UserEntity tempUser = new UserEntity();
		tempUser.setUsername(user.getUsername());
		entityManager.persist(tempUser);

	}

	//@Transactional
	public void updateUser(UserDto user) {
		// finds user by id and updates the username, if there's no id in the
		// obj-> should not update
		entityManager.persist(user);

	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub

	}*/

}
