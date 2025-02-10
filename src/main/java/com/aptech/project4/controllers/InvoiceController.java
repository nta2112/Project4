package com.aptech.project4.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aptech.project4.models.InvoiceDetail;
import com.aptech.project4.repositories.InvoiceRepository;
import com.aptech.project4.repositories.InvoiceDetailRepository;


@Controller
public class InvoiceController {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    @GetMapping("/invoices")
    public String showInvoices(Model model) {
        model.addAttribute("invoices", invoiceRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
        return "invoices";
    }

    @GetMapping("/invoice/details/{invoiceId}")
    @ResponseBody
    public Map<String, Object> getInvoiceDetails(@PathVariable Long invoiceId) {
        List<InvoiceDetail> invoiceDetails = invoiceDetailRepository.findByInvoiceId(invoiceId);

        List<InvoiceDetail> medicines = new ArrayList<>();
        List<InvoiceDetail> services = new ArrayList<>();

        // Phân loại dịch vụ và thuốc
        for (InvoiceDetail detail : invoiceDetails) {
            if (detail.getMedicine() != null) {
                medicines.add(detail);
            } else if (detail.getService() != null) {
                services.add(detail);
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("medicines", medicines);
        response.put("services", services);

        return response;
    }

}
