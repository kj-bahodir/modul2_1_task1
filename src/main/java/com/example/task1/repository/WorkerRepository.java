package com.example.task1.repository;

import com.example.task1.entity.Address;
import com.example.task1.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker,Integer> {
}
