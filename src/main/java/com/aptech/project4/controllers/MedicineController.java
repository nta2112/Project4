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
import com.aptech.project4.repositories.MedicineRepository;

@Controller

public class MedicineController {

    @Autowired
    private MedicineRepository medicineRepository;

    // Danh sách
    @GetMapping("/medicines")
    public String listMedicines(Model model) {
        List<Medicine> medicines = medicineRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("medicines", medicines);
        return "medicines";
    }

    // Thêm
    @PostMapping("/medicines/create")
    @ResponseBody // Đảm bảo rằng phản hồi trả về dưới dạng JSON
    public Medicine addMedicine(@RequestBody Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    // Xóa
    @DeleteMapping("/medicines/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteMedicine(@PathVariable("id") Long id) {
        Optional<Medicine> medicine = medicineRepository.findById(id);
        if (medicine.isPresent()) {
            medicineRepository.delete(medicine.get());
            return ResponseEntity.ok().build(); // Trả về phản hồi thành công
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Thuốc không tồn tại!");
        }
    }

    // Edit
    @GetMapping("/medicines/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable Long id) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy thuốc"));
        return ResponseEntity.ok(medicine);
    }

    // Update
    @PutMapping("/medicines/update/{id}")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable Long id, @RequestBody Medicine medicineDetails) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy thuốc"));

        medicine.setName(medicineDetails.getName());
        medicine.setPrice(medicineDetails.getPrice());

        return ResponseEntity.ok(medicineRepository.save(medicine));
    }

}
