package com.bananes.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;

import com.bananes.model.Destination;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Command {
	 @Id
	 @GeneratedValue
	 private Long id;
	 @JsonFormat(timezone="GMT+2", pattern="yyyy-MM-dd")
	 @NotNull(message = "Please provide a deliver date.")
	 Date deliverDate;
	 @NotNull(message = "Please provide a quantity.")
	 @Min(value = 1, message = "Quantity should not be less than 1")
	 @Max(value = 10000, message = "Quantity should not be greater than 10000")
	 Integer quantity;
	 Float price;
	 @NotNull(message = "Please provide a destination.")
	 @ManyToOne
	 Destination dest;

	public Command(){
		 
	 }
	
	public Command(@NotNull Date deliverDate, @NotNull Integer quantity, @NotNull Destination dest) {
		super();
		this.deliverDate = deliverDate;
		this.quantity = quantity;
		this.price = (float) (quantity * 2.5);
		this.dest = dest;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDeliverDate() {
		return deliverDate;
	}
	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Destination getDest() {
		return dest;
	}
	public void setDest(Destination dest) {
		this.dest = dest;
	}
	 	
}
