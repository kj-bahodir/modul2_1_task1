package com.example.task1.controller;

import com.example.task1.entity.Company;
import com.example.task1.payload.ApiResponse;
import com.example.task1.payload.CompanyDto;
import com.example.task1.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping
    public List<Company> getCompanies(){
        List<Company> companies=companyService.getCompanies();
        return companies;
    }

    @GetMapping("/{id}")
    public Company getCompany(@PathVariable Integer id){
       Company company= companyService.getCompanyById(id);
       return company;
    }

    @PostMapping
    public ApiResponse addCompany(@RequestBody CompanyDto companyDto){
       ApiResponse apiResponse= companyService.addCompany(companyDto);
       return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse editCompany(@PathVariable Integer id, @RequestBody CompanyDto companyDto){
        ApiResponse apiResponse=companyService.editCompany(id,companyDto);
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteCompany(@PathVariable Integer id){
        ApiResponse apiResponse=companyService.deleteCompany(id);
        return apiResponse;
    }



}
