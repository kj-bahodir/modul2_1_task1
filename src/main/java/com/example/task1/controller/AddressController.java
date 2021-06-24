package com.example.task1.controller;

import com.example.task1.entity.Address;
import com.example.task1.payload.AddressDto;
import com.example.task1.payload.ApiResponse;
import com.example.task1.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping
    public ResponseEntity<List<Address>> getAddresses() {
        List<Address> addresses = addressService.getAddresses();
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddress(@PathVariable Integer id) {
        Address address = addressService.getAddressById(id);
        return ResponseEntity.ok(address);
    }

    @PostMapping
    public ResponseEntity<?> addAddress(@RequestBody AddressDto addressDto) {
        ApiResponse apiResponse = addressService.addAddress(addressDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editAddress(@PathVariable Integer id, @RequestBody AddressDto addressDto){
        ApiResponse apiResponse=addressService.editAddress(id,addressDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Integer id){
        ApiResponse apiResponse=addressService.deleteAddress(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handledValidationExeptions(
            MethodArgumentNotValidException ex){
        Map<String,String> errors=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName=((FieldError) error).getField();
            String errorMessage=error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return errors;
    }
}
