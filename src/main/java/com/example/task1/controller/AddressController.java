package com.example.task1.controller;

import com.example.task1.entity.Address;
import com.example.task1.payload.AddressDto;
import com.example.task1.payload.ApiResponse;
import com.example.task1.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping
    public List<Address> getAddresses() {
        List<Address> addresses = addressService.getAddresses();
        return addresses;
    }

    @GetMapping("/{id}")
    public Address getAddress(@PathVariable Integer id) {
        Address address = addressService.getAddressById(id);
        return address;
    }

    @PostMapping
    public ApiResponse addAddress(@RequestBody AddressDto addressDto) {
        ApiResponse apiResponse = addressService.addAddress(addressDto);
        return apiResponse;
    }
    @PutMapping("/{id}")
    public ApiResponse editAddress(@PathVariable Integer id, @RequestBody AddressDto addressDto){
        ApiResponse apiResponse=addressService.editAddress(id,addressDto);
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteAddress(@PathVariable Integer id){
        ApiResponse apiResponse=addressService.deleteAddress(id);
        return apiResponse;
    }
}
