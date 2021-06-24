package com.example.task1.controller;

import com.example.task1.entity.Worker;
import com.example.task1.payload.ApiResponse;
import com.example.task1.payload.WorkerDto;
import com.example.task1.service.WorkerService;
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
@RequestMapping("worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;

    @GetMapping
    public ResponseEntity<List<Worker>>getWorkers(){
      List<Worker> workers= workerService.getWorkers();
      return ResponseEntity.ok(workers);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Worker> getWorker(@PathVariable Integer id){
       Worker worker= workerService.getWorker(id);
       return ResponseEntity.ok(worker);
    }

    @PostMapping
    public ResponseEntity<?> addWorker(@RequestBody WorkerDto workerDto){
        ApiResponse apiResponse=workerService.addWorker(workerDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editWorker(@PathVariable Integer id,@RequestBody WorkerDto workerDto){
        ApiResponse apiResponse=workerService.editWorker(id,workerDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorker(@PathVariable Integer id){
       ApiResponse apiResponse= workerService.deleteWorker(id);
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
