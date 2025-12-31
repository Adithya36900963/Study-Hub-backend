package com.example.StudyHub.service.regulation;
import java.util.List;
import com.example.StudyHub.entity.Regulation;

public interface RegulationServiceLayer {

    Regulation addRegulation(Regulation regulation);

    List<Regulation> getRegulations();

    Regulation getRegulationById(Long id);
}
