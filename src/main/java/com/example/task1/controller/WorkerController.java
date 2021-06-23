package com.example.task1.controller;

import com.example.task1.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;
}
