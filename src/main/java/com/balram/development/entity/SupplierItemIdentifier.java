package com.balram.development.entity;

import jakarta.persistence.*;

import org.springframework.data.domain.Persistable;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "PARENT_TABLE")
@Getter @Setter @ToString @EqualsAndHashCode @Builder
@IdClass(SupplierItemIdentifierId.class)
public class SupplierItemIdentifier implements Persistable<SupplierItemIdentifierId> {

	@Id
	@Column(name = "BU_TYPE", nullable = false)
	private String buType;

	@Id
	@Column(name = "BU_CODE", nullable = false)
	private String buCode;

	@Id
	@Column(name = "SUPPLIER_NUMBER", nullable = false)
	private String supplierNumberString;

	@Id
	@Column(name = "SUPPLIER_TYPE", nullable = false)
	private String supplierType;
	
	@Column(name = "ADDITIONAL_FIELD_1", nullable = false)
	private String additionalField1;
	
	public SupplierItemIdentifier() {
		
	}

	public SupplierItemIdentifier(String string, String string2, String string3, String string4, String string5) {
		this.buCode =string;
		this.buType = string2;
		this.supplierNumberString = string3;
		this.supplierType = string4;
		this.additionalField1 = string5;
	}

	// Added the below to prevent JPA from doing a SELECT+INSERT as the data is always unique
	@Override
	public SupplierItemIdentifierId getId() {
		return new SupplierItemIdentifierId(buType, buCode, supplierNumberString, supplierType);
	}

	@Override
	public boolean isNew() {
		return true;
	}
	
}