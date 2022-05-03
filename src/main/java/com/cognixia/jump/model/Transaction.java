package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "EST")
	private Date datePurchase;
	
	@NotNull
	private Long buyer_id;
	
	@NotNull
	private Long course_id;
	
	@NotBlank
	private String course_name;
	
	@NotBlank
	private String buyer_name;
	
	
	public Transaction() {}


	public Transaction(Long id, Date datePurchase, @NotNull Long buyer_id, @NotNull Long course_id,
			@NotBlank String course_name, @NotBlank String buyer_name) {
		super();
		this.id = id;
		this.datePurchase = datePurchase;
		this.buyer_id = buyer_id;
		this.course_id = course_id;
		this.course_name = course_name;
		this.buyer_name = buyer_name;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getDatePurchase() {
		return datePurchase;
	}


	public void setDatePurchase(Date datePurchase) {
		this.datePurchase = datePurchase;
	}


	public Long getBuyer_id() {
		return buyer_id;
	}


	public void setBuyer_id(Long buyer_id) {
		this.buyer_id = buyer_id;
	}


	public Long getCourse_id() {
		return course_id;
	}


	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}


	public String getCourse_name() {
		return course_name;
	}


	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}


	public String getBuyer_name() {
		return buyer_name;
	}


	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}


	@Override
	public String toString() {
		return "Transaction [id=" + id + ", datePurchase=" + datePurchase + ", buyer_id=" + buyer_id + ", course_id="
				+ course_id + ", course_name=" + course_name + ", buyer_name=" + buyer_name + "]";
	}
	
	
	
	
	
}
