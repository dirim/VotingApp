package com.kodgemisi.votingApp.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ozge on 17.08.2016.
 */
@MappedSuperclass
public abstract class BaseEntity  implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private boolean deleted;

	@Column(nullable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Calendar creationDate;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Calendar updateDate;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Calendar deletionDate;

	public BaseEntity() {
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Calendar updateDate) {
		this.updateDate = updateDate;
	}

	public Calendar getDeletionDate() {
		return deletionDate;
	}

	public void setDeletionDate(Calendar deletionDate) {
		this.deletionDate = deletionDate;
	}

	@PrePersist
	public void prePersist() {
		final Calendar now = Calendar.getInstance();
		this.setCreationDate(now);
		this.setUpdateDate(now);
	}

	@PreUpdate
	public void preUpdate() {
		this.setUpdateDate(Calendar.getInstance());
	}



}
