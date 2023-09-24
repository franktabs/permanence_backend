package com.example.demo.controllers;

import com.example.demo.entities.jasper.PlanningJasper;
import com.example.demo.services.JasperService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "jasper")
public class JasperController {

    @Autowired
    JasperService jasperService;


    @GetMapping("pdf/{id}")
    public ModelAndView generatePdf(HttpServletResponse response, @PathVariable Long id) throws IOException, JRException, SQLException {

        File file = ResourceUtils.getFile("classpath:planning_01.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        PlanningJasper planningJasper1 = jasperService.generatePlanningJasper(id) ;

        List<PlanningJasper> data = new ArrayList<>(List.of(planningJasper1));
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
        Map<String, Object> reportParams = new HashMap<>();
        reportParams.put("title", "PLANNING DE PERMANENCE AOUT A SEPTEMBRE");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportParams, dataSource );
        response.setContentType("application/pdf");
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        return null;
    }



}
