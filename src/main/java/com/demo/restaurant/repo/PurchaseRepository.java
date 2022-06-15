package com.demo.restaurant.repo;

import com.demo.restaurant.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Purchase repository, Extends from JpaRepository which generates the basic crud of the entity
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
