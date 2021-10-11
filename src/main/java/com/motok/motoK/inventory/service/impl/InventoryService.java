package com.motok.motoK.inventory.service.impl;

import com.motok.motoK.inventory.domain.repository.InventoryRepository;
import com.motok.motoK.inventory.service.AbInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService extends AbInventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public void 브랜드관리() {

    }

    @Override
    public void 기종관리() {

    }

    @Override
    public void 재고관리() {

    }
}
