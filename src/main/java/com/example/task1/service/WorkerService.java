package com.example.task1.service;

import com.example.task1.entity.Address;
import com.example.task1.entity.Department;
import com.example.task1.entity.Worker;
import com.example.task1.payload.ApiResponse;
import com.example.task1.payload.WorkerDto;
import com.example.task1.repository.AddressRepository;
import com.example.task1.repository.DepartmentRepository;
import com.example.task1.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public List<Worker> getWorkers() {
        List<Worker> workers = workerRepository.findAll();
        return workers;
    }

    public Worker getWorker(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isPresent()) {
            Worker worker = optionalWorker.get();
            return worker;
        }
        return null;
    }

    public ApiResponse addWorker(WorkerDto workerDto) {
        boolean exists = workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber());
        if (exists) {
            return new ApiResponse("Bunday foydalanuvchi mavjud", false);
        }
        Worker worker = new Worker();

        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            worker.setAddress(address);
        }
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            worker.setDepartment(department);
        }

        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        workerRepository.save(worker);
        return new ApiResponse("Worker added", true);
    }


    public ApiResponse editWorker(Integer id, WorkerDto workerDto) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent()) {
            return new ApiResponse("Worker not found", false);
        }
        Worker worker = optionalWorker.get();
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setName(workerDto.getName());

        Department department = departmentRepository.getById(workerDto.getDepartmentId());
        worker.setDepartment(department);

        Address address = addressRepository.getById(workerDto.getAddressId());
        worker.setAddress(address);

        return new ApiResponse("Worker edited", true);
    }

    public ApiResponse deleteWorker(Integer id) {
        try {
            workerRepository.deleteById(id);
            return new ApiResponse("Worker deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Worker not found", false);
        }
    }
}
