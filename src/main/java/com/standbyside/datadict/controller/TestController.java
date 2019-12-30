package com.standbyside.datadict.controller;

import com.standbyside.datadict.service.DataService;
import com.standbyside.datadict.service.DocService;
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

    @Autowired
    private DocService docService;

    @GetMapping("tables")
    public Object tables() {
        return testService.findTables();
    }

    @GetMapping("pdf")
    public Object pdf() throws IOException, com.itextpdf.text.DocumentException {
        pdfService.generateFile(testService.findTables());
        return "success";
    }

    @GetMapping("doc")
    public Object doc() throws IOException, com.lowagie.text.DocumentException {
        docService.generateFile(testService.findTables());
        return "success";
    }
}
