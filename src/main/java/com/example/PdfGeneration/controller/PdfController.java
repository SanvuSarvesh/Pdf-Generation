package com.example.PdfGeneration.controller;

import com.example.PdfGeneration.model.PdfInfo;
import com.example.PdfGeneration.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @GetMapping("/pdf/generate")
    public ResponseEntity<String> generatePdf(@RequestBody PdfInfo pdfInfo){
        String response = pdfService.generatePdf(pdfInfo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping
    public String swaggerUi() {
        return "redirect:/swagger-ui.html";
    }



}
