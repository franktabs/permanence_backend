package com.example.demo.controllers;

import com.example.demo.entities.jasper.ModelJasper;
import com.example.demo.entities.jasper.PlanningJasper;
import com.example.demo.services.JasperService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(path = "jasper")
public class JasperController {

    @Autowired
    JasperService jasperService;


    @PostMapping("pdf/{id}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable Long id, @RequestBody ModelJasper modelJasper) throws IOException, JRException, SQLException {

        File file = ResourceUtils.getFile("classpath:planning_01.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        PlanningJasper planningJasper1 = jasperService.generatePlanningJasper(id) ;

        List<PlanningJasper> data = new ArrayList<>(List.of(planningJasper1));
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
        Map<String, Object> reportParams = new HashMap<>();
        reportParams.put("title", modelJasper.getTitle());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportParams, dataSource );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "planning-permanence.pdf");

        byte[] pdfByte = JasperExportManager.exportReportToPdf(jasperPrint);
        ByteArrayResource resource = new ByteArrayResource(pdfByte);
        return ResponseEntity.ok().headers(headers).body(pdfByte);
    }



        @PostMapping("/excel/{id}")
        public ResponseEntity<InputStreamResource> exportExcel(HttpServletResponse response, @PathVariable Long id, @RequestBody ModelJasper modelJasper) {
            try {
                // Charger le rapport JasperReports
                File file = ResourceUtils.getFile("classpath:planning_01.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

                if(modelJasper==null){
                    modelJasper=new ModelJasper();
                    modelJasper.setTitle("Plannification mois de octobre à decembre");
                }

                // Récupérer les données pour le rapport, par exemple à partir d'une source de données
                PlanningJasper planningJasper1 = jasperService.generatePlanningJasper(id) ;

                List<PlanningJasper> data = new ArrayList<>(List.of(planningJasper1));
                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);

                Map<String, Object> reportParams = new HashMap<>();
                reportParams.put("title", modelJasper.getTitle());
                // Remplir le rapport avec les données
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportParams, dataSource);

                // Exporter le rapport au format Excel
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                JRXlsxExporter exporter = new JRXlsxExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
                exporter.exportReport();

                // Préparer la réponse HTTP avec le fichier Excel
                byte[] excelBytes = outputStream.toByteArray();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.setContentDispositionFormData("attachment", "plannification-planning.xlsx");
                InputStreamResource inputStreamResource = new InputStreamResource(new ByteArrayInputStream(excelBytes));

                return ResponseEntity.ok()
                        .headers(headers)
                        .body(inputStreamResource);
            } catch (Exception e) {
                e.printStackTrace();
                // Gérer les erreurs et renvoyer une réponse appropriée
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }


    }
