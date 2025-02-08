package com.aptech.project4.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aptech.project4.models.Slot;
import com.aptech.project4.repositories.SlotRepository;

@Controller
public class SlotController {
    @Autowired
    private SlotRepository slotRepository;

    @GetMapping("/slots")
    public String slots(Model model) {
        List<Slot> slots = slotRepository.findAll();
        model.addAttribute("slots", slots);
        return "slots";
    }

    @PostMapping("/slots/update")
    @ResponseBody
    public String updateSlots(@RequestBody List<Slot> slots) {
        slotRepository.deleteAll();
        slotRepository.saveAll(slots);
        return "Cập nhật thành công!";
    }

}
