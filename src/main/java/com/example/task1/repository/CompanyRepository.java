package com.example.task1.repository;

import com.example.task1.entity.Address;
import com.example.task1.entity.Company;
import com.example.task1.payload.CompanyDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
    boolean existsByCorpNameAndDirectorNameAndAddressId(String corpName, String directorName, Integer address_id);

}
