package com.example.task1.service;

import com.example.task1.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;
}
