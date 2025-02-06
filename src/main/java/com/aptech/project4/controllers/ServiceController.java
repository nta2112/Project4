package com.aptech.project4.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.aptech.project4.models.Medicine;
import com.aptech.project4.models.Service;
import com.aptech.project4.repositories.ServiceRepository;

@Controller

public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    // Danh sách
    @GetMapping("/services")
    public String listServices(Model model) {
        List<Service> services = serviceRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("services", services);
        return "services";
    }

    // Thêm
    @PostMapping("/services/create")
    @ResponseBody
    public Service addService(@RequestBody Service service) {
        return serviceRepository.save(service);
    }

    // Xóa
    @DeleteMapping("/services/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteService(@PathVariable("id") Long id) {
        Optional<Service> service = serviceRepository.findById(id);
        if (service.isPresent()) {
            serviceRepository.delete(service.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dịch vụ không tồn tại!");
        }
    }

    // Edit
    @GetMapping("/services/{id}")
    public ResponseEntity<Service> getMedicineById(@PathVariable Long id) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy thuốc"));
        return ResponseEntity.ok(service);
    }

    // Update
    @PutMapping("/services/update/{id}")
    public ResponseEntity<Service> updateService(@PathVariable Long id, @RequestBody Service serviceDetails) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy thuốc"));

        service.setName(serviceDetails.getName());
        service.setPrice(serviceDetails.getPrice());

        return ResponseEntity.ok(serviceRepository.save(service));
    }

}