package com.ironshutter.web.domain.model.customerRequest;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.ironshutter.web.domain.model.account.Account;

@Entity
@Table(name="customer_request_details")
public class CustomerRequestDetail {

	@Id
	@Column(name="request_id")
	private Long requestId;
	
	@Column(name="sequence")
	private Integer sequence;

	@Column(name="content")
	private String content;
	
	@Column(name="created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Column(name="updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	@Column(name="is_deleted")
	@Type(type="org.hibernate.type.NumericBooleanType")
	private Boolean isDeleted = false;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="writer_id")
	private Account requester;
	
}
