package com.aptech.project4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.aptech.project4.models.Shift;
import com.aptech.project4.models.Slot;
import com.aptech.project4.models.User;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
    // Truy vấn các Shift theo doctor (người dùng là bác sĩ)
    List<Shift> findByDoctor(User doctor);

    // Truy vấn các Shift theo slot (thời gian làm việc)
    List<Shift> findBySlot(Slot slot);

    // Truy vấn các Shift theo doctor và slot
    List<Shift> findByDoctorAndSlot(User doctor, Slot slot);

    // Phương thức custom để xóa các ca trực của bác sĩ
    void deleteByDoctor(User doctor);

    // Tìm các shift của bác sĩ theo doctorId
    List<Shift> findByDoctorId(Long doctorId);

    // Phương thức tìm Shift theo doctorId và slotId
    Shift findByDoctorIdAndSlotId(Long doctorId, Long slotId);
}
