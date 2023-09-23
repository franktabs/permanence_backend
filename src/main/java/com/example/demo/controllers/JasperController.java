package com.example.demo.controllers;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
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

    public class Client{
        private String name;

        public Client() {
        }

        public Client(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @GetMapping("pdf")
    public ModelAndView generatePdf(HttpServletResponse response) throws IOException, JRException, SQLException {

        File file = ResourceUtils.getFile("classpath:permanence_01.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        List<Client> data = new ArrayList<>();
        data.add(new Client("Frank"));
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
        Map<String, Object> reportParams = new HashMap<>();
        reportParams.put("data", "myData");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportParams, dataSource );
        response.setContentType("application/pdf");
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        return null;
    }

}
