package com.example.demo.Controller;

import com.example.demo.Service.ItemSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://shopping.rbc.com")
public class ItemOnSaleController {

    @Autowired
    ItemSalesService service;

    @GetMapping(path = "/recommendations/{userId}")
    public String itemsOnsSale(@PathVariable String userId) {
        return this.service.getItemsOnSale(userId);
    }
}
