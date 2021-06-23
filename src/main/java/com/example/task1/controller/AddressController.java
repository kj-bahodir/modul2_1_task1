package com.example.task1.controller;

import com.example.task1.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("address")
public class AddressController {
    @Autowired
    AddressService addressService;
}
