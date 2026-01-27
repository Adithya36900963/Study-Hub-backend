package com.example.StudyHub.service.regulation;
import java.util.List;

import com.example.StudyHub.entity.Regulation;

public interface RegulationServiceLayer {

    //Posting Regulation
    Regulation addRegulation(Regulation regulation);

    //Getting lit of Regulations
    List<Regulation> getRegulations();


    //Global Deletion Regulation by Id
    Regulation deleteRegulationById(Long id);

    //Update Regulation By id
    Regulation updateRegulationById(Long id, Regulation regulation);

    
}
