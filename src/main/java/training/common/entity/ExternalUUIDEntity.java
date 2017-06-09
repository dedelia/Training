package training.common.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

/**
 * Created by tudorg on 3/23/2016.
 */
@MappedSuperclass
public abstract class ExternalUUIDEntity {

	@Column(name = "`EXTERNALUUID`", columnDefinition = "BINARY(16)", length = 16)
	@NotNull
	private UUID externalUUID;

	public UUID getExternalUUID() {
		return externalUUID;
	}

	public void setExternalUUID(UUID externalUUID) {
		this.externalUUID = externalUUID;
	}

	public String getExternalUUIDWithoutDashes() {
		if (getExternalUUID() != null) {
			return getExternalUUID().toString().replace("-", "");
		}
		return null;
	}

	@PrePersist
	public void onCreate() {
		if (externalUUID == null) {
			externalUUID = UUID.randomUUID();
		}
	}

}
