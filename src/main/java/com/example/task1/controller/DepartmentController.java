package com.example.task1.controller;

import com.example.task1.entity.Department;
import com.example.task1.payload.ApiResponse;
import com.example.task1.payload.DepartmentDto;
import com.example.task1.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public List<Department> getDepartments(){
      List<Department> departments=  departmentService.getDepartments();
      return departments;
    }

    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable Integer id){
        Department department=departmentService.getDepartment(id);
        return department;
    }
    @PostMapping
    public ApiResponse addDepartment(@RequestBody DepartmentDto departmentDto){
        ApiResponse apiResponse=departmentService.addDepartment(departmentDto);
        return apiResponse;
    }
    @PostMapping("/{id}")
    public ApiResponse editDepartment(@PathVariable Integer id, @RequestBody DepartmentDto departmentDto){
        ApiResponse apiResponse=departmentService.editDepartment(id,departmentDto);
        return apiResponse;
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteDepartment(@PathVariable Integer id){
        ApiResponse apiResponse=departmentService.deleteDepartment(id);
        return apiResponse;
    }

}
