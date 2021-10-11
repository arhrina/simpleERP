package com.motok.motoK.inventory.domain.repository;

import com.motok.motoK.inventory.domain.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
