package com.motok.motoK.customer.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list() {
        return "/customer/고객목록";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newCustomer() {
        return "/customer/고객관리-신규";
    }

    @RequestMapping(value = "/customCard", method = RequestMethod.GET)
    public String customerCard() {
        return "/customer/정비카드";
    }
}
