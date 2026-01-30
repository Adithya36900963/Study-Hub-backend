package com.example.StudyHub.controller;


import com.example.StudyHub.model.PDFModel;
import com.example.StudyHub.model.ResponseModel;
import com.example.StudyHub.service.pdf.PDFServiceLayer;


import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/pdfs")
public class PDFController {

    private final PDFServiceLayer pdfService;
   

    public PDFController(
            PDFServiceLayer pdfService) 
    {
        this.pdfService = pdfService;

    }

    //Post PDF
    @Transactional
    @PostMapping(value = "/{subjectId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String,Object>> upload(
            @PathVariable Long subjectId,
            @RequestParam String name,
            @RequestParam MultipartFile pdf) throws Exception {

        ResponseModel<PDFModel> res=new ResponseModel<>(201, "Created sucessflly",pdfService.createPDFSAndPDF(subjectId,name,pdf));
        return res.res();
        
    }


   

    // ✅ LIST
    @GetMapping("/{subjectId}")
    public ResponseEntity<Map<String,Object>> list(
            @PathVariable Long subjectId) {

        ResponseModel<List<PDFModel>> res=new ResponseModel<>(200, "Fetched sucessflly",pdfService.getPDFModel(subjectId));
        return res.res();
    }

    // ✅ DOWNLOAD
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Long id) {
        return pdfService.downloadPDF(id);
    }
}
