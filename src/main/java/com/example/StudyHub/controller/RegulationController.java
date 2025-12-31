package com.example.StudyHub.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StudyHub.entity.Regulation;
import com.example.StudyHub.service.regulation.RegulationServiceLayer;

import java.util.List;

@RestController
@RequestMapping("api/regulations")
@CrossOrigin(origins = "http://localhost:3000")
public class RegulationController {
    private RegulationServiceLayer serviceLayer;
    public RegulationController(RegulationServiceLayer serviceLayer){
        this.serviceLayer = serviceLayer;
    }


    @PostMapping
    public Regulation createRegulation(@RequestBody Regulation regulation){
        return serviceLayer.addRegulation(regulation);
    }
    
    @GetMapping
    public List<Regulation> getRegulations() {
        return serviceLayer.getRegulations();
    }
}
