package com.balram.development.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@ToString 
@NoArgsConstructor 
@EqualsAndHashCode 
@AllArgsConstructor 
@Builder
public class SupplierItemIdentifierId implements Serializable {
	
	
	private static final long serialVersionUID = -3167948868931342247L;
	
	private String buType;
	private String buCode;
	private String supplierNumberString;
	private String supplierType;
	
	public SupplierItemIdentifierId() {
		
	}
	
	public SupplierItemIdentifierId(String buType, String buCode, String supplierNumberString, String supplierType) {
		this.buCode =buCode;
		this.buType = buType;
		this.supplierNumberString = supplierNumberString;
		this.supplierType = supplierType;
		
	}
}
