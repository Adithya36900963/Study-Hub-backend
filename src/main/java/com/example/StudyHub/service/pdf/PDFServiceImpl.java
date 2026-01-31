package com.example.StudyHub.service.pdf;

import com.example.StudyHub.entity.PDF;
import com.example.StudyHub.model.PDFModel;
import com.example.StudyHub.repository.PDFRepository;
import com.example.StudyHub.repository.PDFSRepository;
import com.example.StudyHub.repository.PDFSSubjectRepository;
import com.example.StudyHub.repository.SubjectRepository;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class PDFServiceImpl implements PDFServiceLayer {

    private final SubjectRepository subjectRepo;
    private final PDFSRepository pdfsRepo;
    private final PDFRepository pdfRepo;
    private final PDFSSubjectRepository pdfssRepo;

    public PDFServiceImpl(
            SubjectRepository subjectRepo,
            PDFSRepository pdfsRepo,
            PDFRepository pdfRepo,
            PDFSSubjectRepository pdfssRepo
    ) {
        this.subjectRepo = subjectRepo;
        this.pdfsRepo = pdfsRepo;
        this.pdfRepo = pdfRepo;
        this.pdfssRepo = pdfssRepo;
    }

    //Create PDF
    @Transactional
    @Override
    public PDFModel createPDFSAndPDF(Long subjectId, String name, MultipartFile file) throws Exception {

        byte[] pdfBytes = convertToPDFIfNeeded(file);
        
        PDF pdf = PDF.builder()
                .name(convertToPdfName(name))   // always save as .pdf
                .data(pdfBytes)
                .build();

        pdfRepo.save(pdf);

        return new PDFModel(pdf.getId(), pdf.getName());
    }


    //GET ALL PDFs
    @Transactional(readOnly = true)
    @Override
    public List<PDFModel> getPDFModel(Long subjectId) {

        List<PDFModel> list = pdfssRepo.findPDFModelsBySubjectId(subjectId);

        if(list==null)
            return List.of();

        return list;
    }


    
    //DOWNLOAD PDF
    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<byte[]> downloadPDF(Long id) {

        PDF pdf = pdfRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("PDF not found with id: " + id));

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)   // IMPORTANT
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + pdf.getName() + "\""
                )
                .body(pdf.getData());
    }


    //Converts to PDF if it not PDF already
    public byte[] convertToPDFIfNeeded(MultipartFile file) throws Exception {

        String name = file.getOriginalFilename().toLowerCase();

        // Already PDF
        if (name.endsWith(".pdf")) {
            return file.getBytes();
        }

        Path tempDir = Files.createTempDirectory("convert");

        try {
            Path inputFile = tempDir.resolve(name);

            Files.write(inputFile, file.getBytes());

            ProcessBuilder pb = new ProcessBuilder(
                    "soffice",               // LibreOffice command
                    "--headless",
                    "--convert-to", "pdf",
                    inputFile.toAbsolutePath().toString(),
                    "--outdir",
                    tempDir.toAbsolutePath().toString()
            );

            pb.redirectErrorStream(true);

            Process process = pb.start();
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                throw new RuntimeException("LibreOffice conversion failed");
            }

            String pdfName = convertToPdfName(name);

            Path outputFile = tempDir.resolve(pdfName);

            return Files.readAllBytes(outputFile);

        } finally {
            // cleanup temp files safely
            Files.walk(tempDir)
                    .sorted((a, b) -> b.compareTo(a))
                    .forEach(p -> p.toFile().delete());
        }
    }


    
    private String convertToPdfName(String name) {
        return name.substring(0, name.lastIndexOf(".")) + ".pdf";
    }
}
