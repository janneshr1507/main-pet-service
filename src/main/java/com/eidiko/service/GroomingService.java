package com.eidiko.service;

import com.eidiko.dto.GroomingDTO;
import com.eidiko.entity.Grooming;
import com.eidiko.repository.GroomingRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroomingService {
    private final GroomingRepository groomingRepo;
    private final ModelMapper modelMapper;

    public GroomingDTO saveGroomingSchedule(GroomingDTO groomingDTO) {
        Grooming grooming = modelMapper.map(groomingDTO, Grooming.class);
        return modelMapper.map(groomingRepo.save(grooming), GroomingDTO.class);
    }

    public List<GroomingDTO> getAllGroomingSchedules() {
        List<Grooming> groomingScheduleList = groomingRepo.findAll();
        List<GroomingDTO> groomingDTOScheduleList = new ArrayList<>();
        for(int i=0; i<groomingScheduleList.size(); i++) {
            GroomingDTO groomingDTO = modelMapper.map(groomingScheduleList.get(i), GroomingDTO.class);
            groomingDTOScheduleList.add(groomingDTO);
        }
        return groomingDTOScheduleList;
    }
}
