package com.aptech.project4.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.aptech.project4.models.Slot;

public interface SlotRepository extends JpaRepository<Slot, Long> {
}