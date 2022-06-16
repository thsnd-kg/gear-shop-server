package com.techshop.report.controller;

import com.techshop.common.ResponseHandler;
import com.techshop.importer.converter.ImportConverter;
import com.techshop.importer.dto.GetImporterDto;
import com.techshop.importer.service.ImporterService;
import com.techshop.order.converter.OrderConverter;
import com.techshop.order.dto.order.GetOrderDto;
import com.techshop.order.service.OrderService;
import com.techshop.report.service.ReportService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/report")
public class ReportController {
    private OrderService orderService;
    private OrderConverter orderConverter;
    private ImporterService importerService;
    private ImportConverter importConverter;


    public ReportController(OrderService orderService,  OrderConverter orderConverter, ImporterService importerService, ImportConverter importConverter) {
        this.orderService = orderService;
        this.orderConverter = orderConverter;
        this.importerService = importerService;
        this.importConverter = importConverter;
    }

    @GetMapping("/orders")
    public Object getOrderReport(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
                                 @RequestParam String compression){
        try{

            Map<LocalDate, List<GetOrderDto>> report = orderService.getOrderReport(start, end, compression)
                    .entrySet().stream().collect(
                            Collectors.toMap(Map.Entry::getKey,
                                    e -> e.getValue().stream().map(item -> orderConverter.toGetOrderDto(item))
                                            .collect(Collectors.toList())));
            return ResponseHandler.getResponse(report, HttpStatus.OK);
        } catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/import")
    public Object getImportReport(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
                                 @RequestParam String compression){
        try {
            Map<LocalDate, List<GetImporterDto>> result = importerService.getImportReport(start, end, compression)
                    .entrySet().stream().collect(
                            Collectors.toMap(Map.Entry::getKey,
                                    e -> e.getValue().stream().map(item -> importConverter.toGetImportDto(item))
                                            .collect(Collectors.toList())));

            return ResponseHandler.getResponse(result, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/orders/revenue")
    public Object getRevenue() {
        try {
            Object result = orderService.getRevenue();
            return ResponseHandler.getResponse(result, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/orders/best-seller")
    public Object bestSeller() {
        try {
            return ResponseHandler.getResponse(orderService.getBestSeller(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/import/total-cost")
    public Object getTotalCost() {
        try {
            return ResponseHandler.getResponse(importerService.getTotalCost(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }
}
