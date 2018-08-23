package com.example.HotelOrderMgmntDemo.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class UserOrder {
	
private static final long serialVersionUID = 0x62A6DA99AABDA8A8L;
	
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long id;
	
	private String description;
	
	private String address;
	
	private String phone;

	private String status;
	
	@Temporal(TemporalType.DATE)
	private Date createdAt;
	
	@Temporal(TemporalType.DATE)
	private Date updatedDate;

	private String orderBy;

	private String updatedBy;
	
	public UserOrder() {
		super();
	}
	
	public String getStatus() {
		if (org.springframework.util.StringUtils.isEmpty(status)){
			return "ordered";
		}else{
			return status;
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "UserOrder [id=" + id + ", description=" + description + ", address=" + address + ", phone=" + phone
				+ ", status=" + status + ", createdAt=" + createdAt + ", updatedDate=" + updatedDate + ", orderBy="
				+ orderBy + ", updatedBy=" + updatedBy + "]";
	}
	
}