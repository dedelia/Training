package training.common.repository;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;


public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements BaseRepository<T, ID> {

	// first cool thing noticed : SimpleJpaRepository implements the
	// JpaRepository interface's methods !! :D

	private EntityManager entityManager;

	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findByExternalUUID(String externalUUIDWithoutDashes) {
		UUID uuid = getUUIDfromStringWithoutDashes(externalUUIDWithoutDashes);

		return (T) entityManager
				.createQuery("select obj from " + getDomainClass().getName() + " obj where obj.externalUUID = :uuid")
				.setParameter("uuid", uuid).getResultList().stream().findFirst().orElse(null);
	}

	private UUID getUUIDfromStringWithoutDashes(String externalUUIDWithoutDashes) {
		String externalUUIDWithDashes = externalUUIDWithoutDashes.replaceFirst(
				"([0-9a-fA-F]{8})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]+)", "$1-$2-$3-$4-$5");
		return UUID.fromString(externalUUIDWithDashes);
	}

	@SuppressWarnings("unchecked")
	//@Override
	public List<T> findAllObjects() {
		return (List<T>) entityManager.createQuery("select obj from " + getDomainClass().getName() + " obj ")
				.getResultList();
	}
}
