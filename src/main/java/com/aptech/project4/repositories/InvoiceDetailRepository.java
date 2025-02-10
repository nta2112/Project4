package com.aptech.project4.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aptech.project4.models.InvoiceDetail;

@Repository
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Long> {
    // Phương thức tìm các chi tiết hóa đơn theo id hóa đơn (invoiceId)
    List<InvoiceDetail> findByInvoiceId(Long invoiceId);
}
