package com.aptech.project4.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aptech.project4.models.Appointment;
import com.aptech.project4.models.Shift;
import com.aptech.project4.models.Slot;
import com.aptech.project4.models.Ticket;
import com.aptech.project4.models.User;
import com.aptech.project4.repositories.AppointmentRepository;
import com.aptech.project4.repositories.ShiftRepository;
import com.aptech.project4.repositories.TicketRepository;
import com.aptech.project4.repositories.UserRepository;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ShiftRepository shiftRepository;

    @GetMapping("/appointments")
    public String showAppointments(Model model) {
        model.addAttribute("appointments", appointmentRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
        model.addAttribute("receptionists", userRepository.findByRole("Lễ tân"));
        model.addAttribute("tickets", ticketRepository.findAll());
        model.addAttribute("doctors", userRepository.findByRole("Bác sĩ"));
        return "appointments";
    }

    // API để lấy các ca trực (time slots) theo bác sĩ
    @GetMapping("/slots-by-doctor/{doctorId}")
    @ResponseBody
    public List<Slot> getSlotsByDoctor(@PathVariable Long doctorId) {
        List<Shift> shifts = shiftRepository.findByDoctorId(doctorId);
        List<Slot> slots = new ArrayList<>();
        for (Shift shift : shifts) {
            slots.add(shift.getSlot());
        }
        return slots;
    }

    // API POST để thêm lịch hẹn
    @PostMapping("/appointments/add")
    public String addAppointment(@RequestParam Long receptionistId, @RequestParam Long ticketId,
            @RequestParam Long doctorId, @RequestParam Long slotId, RedirectAttributes redirectAttributes) {
        User receptionist = userRepository.findById(receptionistId).orElse(null);
        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
        Shift shift = shiftRepository.findByDoctorIdAndSlotId(doctorId, slotId);

        if (receptionist != null && ticket != null && shift != null) {
            Appointment appointment = new Appointment();
            appointment.setReceptionist(receptionist);
            appointment.setTicket(ticket);
            appointment.setShift(shift);
            appointmentRepository.save(appointment);

            redirectAttributes.addFlashAttribute("message", "Thêm lịch hẹn thành công!");
        }

        return "redirect:/appointments";
    }

    // Xóa
    @DeleteMapping("/appointments/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteAppointment(@PathVariable("id") Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (appointment.isPresent()) {
            appointmentRepository.delete(appointment.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dịch vụ không tồn tại!");
        }
    }

    // edit
    // API GET để lấy thông tin lịch hẹn cần chỉnh sửa
    @GetMapping("/appointments/edit/{id}")
    @ResponseBody
    public Appointment getAppointmentForEdit(@PathVariable Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    // API POST để cập nhật lịch hẹn
    @PostMapping("/appointments/update")
    public String updateAppointment(@RequestParam Long appointmentId,
            @RequestParam Long receptionistId,
            @RequestParam Long ticketId,
            @RequestParam Long doctorId,
            @RequestParam Long slotId,
            RedirectAttributes redirectAttributes) {

        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
        User receptionist = userRepository.findById(receptionistId).orElse(null);
        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
        Shift shift = shiftRepository.findByDoctorIdAndSlotId(doctorId, slotId);

        if (appointment != null && receptionist != null && ticket != null && shift != null) {
            // Cập nhật các thông tin lịch hẹn
            appointment.setReceptionist(receptionist);
            appointment.setTicket(ticket);
            appointment.setShift(shift);

            // Lưu lại cập nhật
            appointmentRepository.save(appointment);

            redirectAttributes.addFlashAttribute("message", "Cập nhật lịch hẹn thành công!");
        } else {
            redirectAttributes.addFlashAttribute("message", "Có lỗi xảy ra, vui lòng thử lại!");
        }

        return "redirect:/appointments";
    }

}
