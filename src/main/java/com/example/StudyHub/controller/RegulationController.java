package com.example.StudyHub.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StudyHub.entity.Regulation;

import com.example.StudyHub.service.regulation.RegulationServiceLayer;

import java.util.List;
import java.util.Map;

import com.example.StudyHub.model.ResponseModel;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("api/regulations")
@CrossOrigin(origins = "http://localhost:3000")
public class RegulationController {
    private RegulationServiceLayer rsl;
    public RegulationController(RegulationServiceLayer rsl){
        this.rsl = rsl;
    }

    //Get List of Regulations
    @GetMapping
    public ResponseEntity<Map<String,Object>> getRegulations() {
        ResponseModel<List<Regulation>> res=new ResponseModel<>(200, " Fetched sucessfully", rsl.getRegulations());
        return res.res();
    }
  
    //Creating Regulation 
    @PostMapping
    public ResponseEntity<Map<String,Object>> createRegulation(@RequestBody Regulation regulation){
        ResponseModel<Regulation> res=new ResponseModel<>(201, "Created sucessfully",rsl.addRegulation(regulation));
        return res.res();

    }
    

    //Deleting Regulation
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>>  deleteRegulation(@PathVariable Long id)
    {
        ResponseModel<Regulation> res=new ResponseModel<>(200, "Deleted sucessflly",rsl.deleteRegulationById(id));
        return res.res();
      
        
    }
 
    //Update Regulation
    @PatchMapping("/{id}")
    public ResponseEntity<Map<String,Object>> updateRegulation(@PathVariable Long id,@RequestBody Regulation regulation)
    {
        ResponseModel<Regulation> res=new ResponseModel<>(200, "Updated Sucessfully",rsl.updateRegulationById(id, regulation));
        return res.res();

    }
}
