package com.example.task1.service;

import com.example.task1.entity.Address;
import com.example.task1.entity.Company;
import com.example.task1.payload.ApiResponse;
import com.example.task1.payload.CompanyDto;
import com.example.task1.repository.AddressRepository;
import com.example.task1.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;

    public List<Company> getCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies;
    }

    public Company getCompanyById(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.orElse(null);
    }

    public ApiResponse addCompany(CompanyDto companyDto) {
        Company company = new Company();
        boolean exists = companyRepository.existsByCorpNameAndDirectorNameAndAddressId(companyDto.getCorpName(), companyDto.getDirectorName(), companyDto.getAddressId());
        if (exists) {
            return new ApiResponse("Company exists", false);
        }
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            company.setAddress(address);
            company.setCorpName(companyDto.getCorpName());
            company.setDirectorName(companyDto.getDirectorName());
            companyRepository.save(company);
            return new ApiResponse("Successfully added", true);
        }
        return new ApiResponse("Such address not found",false);
    }


}
