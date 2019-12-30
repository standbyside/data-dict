package com.standbyside.datadict.controller;

import com.itextpdf.text.DocumentException;
import com.standbyside.datadict.service.DataService;
import com.standbyside.datadict.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TestController {

    @Autowired
    private DataService testService;

    @Autowired
    private PdfService pdfService;

    @GetMapping("tables")
    public Object tables() {
        return testService.findTables();
    }

    @GetMapping("pdf")
    public Object pdf() throws IOException, DocumentException {
        pdfService.generateFile(testService.findTables());
        return "success";
    }
}
