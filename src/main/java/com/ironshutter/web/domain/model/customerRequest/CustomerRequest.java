package com.ironshutter.web.domain.model.customerRequest;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ironshutter.web.domain.model.account.Account;

@Entity
@Table(name="customer_request")
public class CustomerRequest {

	@Id
	@Column(name="id")
	private String id;

	@Column(name="state")
	@Enumerated(EnumType.STRING)
	private CustomerRequestState customerRequestState;
	
	@Column(name="requested_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date requestedAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="requester_id")
	private Account requester;

}
