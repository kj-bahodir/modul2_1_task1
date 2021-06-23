package com.example.task1.controller;

import com.example.task1.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
}
