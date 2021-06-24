package com.example.task1.controller;

import com.example.task1.entity.Department;
import com.example.task1.payload.ApiResponse;
import com.example.task1.payload.DepartmentDto;
import com.example.task1.service.DepartmentService;
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
@RequestMapping("department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public ResponseEntity< List<Department>> getDepartments(){
      List<Department> departments=  departmentService.getDepartments();
      return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity< Department> getDepartment(@PathVariable Integer id){
        Department department=departmentService.getDepartment(id);
        return ResponseEntity.ok(department);
    }
    @PostMapping
    public ResponseEntity<?> addDepartment(@RequestBody DepartmentDto departmentDto){
        ApiResponse apiResponse=departmentService.addDepartment(departmentDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editDepartment(@PathVariable Integer id, @RequestBody DepartmentDto departmentDto){
        ApiResponse apiResponse=departmentService.editDepartment(id,departmentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Integer id){
        ApiResponse apiResponse=departmentService.deleteDepartment(id);
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
