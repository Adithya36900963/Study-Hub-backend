package com.example.StudyHub.service.regulation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.StudyHub.entity.Regulation;
import com.example.StudyHub.repository.RegulationRepository;

@Service
public class RegulationServiceImpl implements RegulationServiceLayer {

    private RegulationRepository regulationRepository;

    public RegulationServiceImpl(RegulationRepository regulationRepository) {
        this.regulationRepository = regulationRepository;
    }

    @Override
    public Regulation addRegulation(Regulation regulation) {
        Regulation existingRegulation=regulationRepository.findByName(regulation.getName());
        if(existingRegulation!=null){
            return existingRegulation;
        }
        return regulationRepository.save(regulation);
    }

    @Override
    public List<Regulation> getRegulations() {
        List<Regulation> regulations=regulationRepository.findAll();
        if(regulations.isEmpty()){
            return null;
        }
        return regulations;
    }

    @Override
    public Regulation getRegulationById(Long id) {
        return regulationRepository.findById(id).orElse(null);
    }
    

    
    
}
