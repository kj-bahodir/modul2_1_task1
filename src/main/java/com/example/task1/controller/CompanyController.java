package com.example.task1.controller;

import com.example.task1.entity.Company;
import com.example.task1.payload.ApiResponse;
import com.example.task1.payload.CompanyDto;
import com.example.task1.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping
    public ResponseEntity< List<Company>> getCompanies(){
        List<Company> companies=companyService.getCompanies();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Integer id){
       Company company= companyService.getCompanyById(id);
       return ResponseEntity.ok(company);
    }

    @PostMapping
    public ResponseEntity<?> addCompany(@RequestBody CompanyDto companyDto){
       ApiResponse apiResponse= companyService.addCompany(companyDto);
       return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editCompany(@PathVariable Integer id, @RequestBody CompanyDto companyDto){
        ApiResponse apiResponse=companyService.editCompany(id,companyDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable Integer id){
        ApiResponse apiResponse=companyService.deleteCompany(id);
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
