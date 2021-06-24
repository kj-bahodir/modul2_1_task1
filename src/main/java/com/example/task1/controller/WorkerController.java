package com.example.task1.controller;

import com.example.task1.entity.Worker;
import com.example.task1.payload.ApiResponse;
import com.example.task1.payload.WorkerDto;
import com.example.task1.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;

    @GetMapping
    public List<Worker> getWorkers(){
      List<Worker> workers= workerService.getWorkers();
      return workers;
    }
    @GetMapping("/{id}")
    public Worker getWorker(@PathVariable Integer id){
       Worker worker= workerService.getWorker(id);
       return worker;
    }

    @PostMapping
    public ApiResponse addWorker(@RequestBody WorkerDto workerDto){
        ApiResponse apiResponse=workerService.addWorker(workerDto);
        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse editWorker(@PathVariable Integer id,@RequestBody WorkerDto workerDto){
        ApiResponse apiResponse=workerService.editWorker(id,workerDto);
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteWorker(@PathVariable Integer id){
       ApiResponse apiResponse= workerService.deleteWorker(id);
       return apiResponse;
    }

}
