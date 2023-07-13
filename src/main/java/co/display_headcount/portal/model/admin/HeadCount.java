package co.display_headcount.portal.model.admin;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="head_count")
public class HeadCount  implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="head_count_id")
	private Integer headCountId;
	
	@Column(name="person_count")
	private Integer personCount;
	
	@Column(name="allowed_person")
	private Integer maxAllowedPerson;
	
	@Column(name="new_allowed_person")
	private Integer newAllowedPerson;
	
	@Column(name="created_by")
	public String createdBy ;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="created_on")
	public Date createdOn;
	
	@Column(name="modified_by")
	public String modifiedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="modified_on")
	public Date modifiedOn;

	public Integer getHeadCountId() {
		return headCountId;
	}

	public void setHeadCountId(Integer headCountId) {
		this.headCountId = headCountId;
	}

	public Integer getPersonCount() {
		return personCount;
	}

	public void setPersonCount(Integer personCount) {
		this.personCount = personCount;
	}

	public Integer getMaxAllowedPerson() {
		return maxAllowedPerson;
	}

	public void setMaxAllowedPerson(Integer maxAllowedPerson) {
		this.maxAllowedPerson = maxAllowedPerson;
	}

	public Integer getNewAllowedPerson() {
		return newAllowedPerson;
	}

	public void setNewAllowedPerson(Integer newAllowedPerson) {
		this.newAllowedPerson = newAllowedPerson;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	
	

}

