package com.techshop.report.service;

import com.techshop.order.entity.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ReportService {
    Map<LocalDate, List<Order>> getOrderReport(LocalDate start, LocalDate end, String compression);
}
