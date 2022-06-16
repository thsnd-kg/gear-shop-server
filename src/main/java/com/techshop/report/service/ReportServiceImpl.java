package com.techshop.report.service;

import com.techshop.order.entity.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements  ReportService{
    @Override
    public Map<LocalDate, List<Order>> getOrderReport(LocalDate start, LocalDate end, String compression) {
        return null;
    }
}
