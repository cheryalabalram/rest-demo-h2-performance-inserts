package com.balram.development.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balram.development.entity.SupplierItemIdentifier;
import com.balram.development.entity.SupplierItemIdentifierId;

@Repository
public interface PerformanceRepo extends JpaRepository<SupplierItemIdentifier, SupplierItemIdentifierId> {

}
