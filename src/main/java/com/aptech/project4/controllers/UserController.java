package com.aptech.project4.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aptech.project4.models.Shift;
import com.aptech.project4.models.User;
import com.aptech.project4.models.UserEditDTO;
import com.aptech.project4.models.Slot;
import com.aptech.project4.repositories.ShiftRepository;
import com.aptech.project4.repositories.SlotRepository;
import com.aptech.project4.repositories.UserRepository;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private ShiftRepository shiftRepository;

    // Danh sách
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("users", users);
        return "users";
    }

    // Thêm
    @PostMapping("/users/create")
    @ResponseBody
    public User addUser(@RequestBody User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    // Xóa
    @DeleteMapping("/users/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("người dùng không tồn tại!");
        }
    }

    // Edit pro max
    @GetMapping("/users/edit/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getUserForEdit(@PathVariable("id") Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "User not found"));
        }

        User user = userOptional.get();

        List<Slot> slots = slotRepository.findAll();

        List<Shift> shifts = shiftRepository.findByDoctor(user);

        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        response.put("slots", slots);
        response.put("shifts", shifts);

        return ResponseEntity.ok(response);
    }

    // Update User and Shifts
    @Transactional
    @PutMapping("/users/edit/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateUserAndShifts(
            @PathVariable("id") Long id,
            @RequestBody UserEditDTO userEditDTO) {

        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "User not found"));
        }

        User user = userOptional.get();

        // mã hóa mk
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userEditDTO.getPassword());

        // Cập nhật thông tin người dùng
        user.setFullName(userEditDTO.getFullName());
        user.setUsername(userEditDTO.getUsername());
        user.setPhone(userEditDTO.getPhone());
        user.setPassword(encodedPassword);
        user.setEmail(userEditDTO.getEmail());
        user.setAddress(userEditDTO.getAddress());
        user.setRole(userEditDTO.getRole());
        userRepository.save(user);

        // Xóa ca trực cũ của bác sĩ
        shiftRepository.deleteByDoctor(user);

        // Thêm ca trực mới vào bảng shifts
        for (Long slotId : userEditDTO.getSlotIds()) {
            Slot slot = slotRepository.findById(slotId).orElseThrow(() -> new RuntimeException("Slot not found"));
            Shift shift = new Shift();
            shift.setDoctor(user);
            shift.setSlot(slot);
            shiftRepository.save(shift);
        }

        return ResponseEntity.ok(Map.of("message", "Cập nhật thành công !"));
    }

}
