package com.order.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "TBL_ORDER_ITEMS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderLineItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name ="PRODUCT_ID", nullable = false)
	private Long productId;
	
	@Column(name ="QTY_ORDERED", nullable = false)
	private int quantityOrdered;
	
	@Column(name ="UNIT_PRICE", nullable = false)
	private Double unitPrice;
	
	@Column(name ="UOM", nullable = false)
	@JsonIgnore
	private String uom;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID", nullable = false)
	Order order;

}
