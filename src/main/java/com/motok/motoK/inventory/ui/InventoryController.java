package com.motok.motoK.inventory.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/inventory")
public class InventoryController {
    @RequestMapping("/list")
    public String inventoryList() {
        return "/inventory/재고관리";
    }
}
