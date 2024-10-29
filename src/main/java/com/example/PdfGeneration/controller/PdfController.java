package com.example.PdfGeneration.controller;

import com.example.PdfGeneration.model.PdfInfo;
import com.example.PdfGeneration.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pdf/generate")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @GetMapping
    public ResponseEntity<String> generatePdf(@RequestBody PdfInfo pdfInfo){
        String response = pdfService.generatePdf(pdfInfo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
