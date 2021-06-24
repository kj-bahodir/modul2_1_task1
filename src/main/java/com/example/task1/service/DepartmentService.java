package com.example.task1.service;

import com.example.task1.entity.Company;
import com.example.task1.entity.Department;
import com.example.task1.payload.ApiResponse;
import com.example.task1.payload.DepartmentDto;
import com.example.task1.repository.CompanyRepository;
import com.example.task1.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;

    public List<Department> getDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments;
    }

    public Department getDepartment(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.orElse(null);
    }

    public ApiResponse addDepartment(DepartmentDto departmentDto) {
        Department department = new Department();
        boolean exists = departmentRepository.existsByName(departmentDto.getName());
        if (exists) {
            return new ApiResponse("Depmartment exisis", false);
        }
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            department.setCompany(company);
        }
        department.setName(departmentDto.getName());
        departmentRepository.save(department);
        return new ApiResponse("Department added", true);
    }

    public ApiResponse editDepartment(Integer id, DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()) {
            return new ApiResponse("Department not found", false);
        }
        Department department = optionalDepartment.get();
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (optionalDepartment.isPresent()) {
            Company company = optionalCompany.get();
            department.setCompany(company);
        }
        department.setName(department.getName());
        departmentRepository.save(department);
        return new ApiResponse("Department edited", true);
    }

    public ApiResponse deleteDepartment(Integer id) {
        try {
            departmentRepository.deleteById(id);
            return new ApiResponse("Department deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Department not found", false);
        }

    }
}
