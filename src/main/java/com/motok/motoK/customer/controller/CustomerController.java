package com.motok.motoK.customer.controller;

import com.motok.motoK.customer.domain.HistoryVO;
import com.motok.motoK.domain.vo.CustomerVO;
import com.motok.motoK.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String customerList() {
        return "/customer/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newCustomer() {
        return "/customer/newCustomer";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public String newCustomer(@RequestParam(required = true, name = "customer") CustomerVO vo) {
        int insert = customerService.insert(vo);
         if(insert > 0)
             return "SAVE";
         else
             return "FAIL";
    }

    @RequestMapping(value = "/addHistory", method = RequestMethod.GET)
    public String makeHistory() {
        return "/customer/addHistory";
    }

    @RequestMapping(value = "/addHistory", method = RequestMethod.POST)
    public String makeHistory(@RequestParam(name = "history") HistoryVO vo) {
        return null;
    }
}
