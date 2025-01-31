package com.jiabin.redis.mybatis.plus.practice.controller;

import com.jiabin.redis.mybatis.plus.practice.service.CouponService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class ExportController {
    

    @Autowired
    private CouponService couponService;
    
    //生成导出 CSV 文件
    @GetMapping("/export-coupons/csv")
    public void exportCoupons(HttpServletResponse response) {
        
        String fileName = "coupons.csv";
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setContentType("text/csv; charset=UTF-8");
        couponService.exportCouponsToCSV(response);
    
    }
    
    //生成导出 pdf 文件
    @GetMapping("/export-coupons/pdf")
    public void exportCouponsPdf(HttpServletResponse response) throws IOException, DocumentException {
        String fileName = "coupons.pdf"; 
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setContentType("application/pdf; charset=UTF-8");
        
        couponService.exportCouponsToPDF(response);
    }
    
    //生成导出 excel 文件
    @GetMapping("/export-coupons/excel")
    public void exportCouponsExcel(HttpServletResponse response) throws IOException {
        String fileName = "coupons.xlsx"; 
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    
        couponService.exportCouponsToExcel(response);
    }
    
    @GetMapping("/export-test")
    public String exportTestPage(Model model) {
        return "/coupon/export-test"; 
    }

}