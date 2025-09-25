package com.eidiko.service;

import com.eidiko.dto.AppointmentDTO;
import com.eidiko.dto.GroomingDTO;
import com.eidiko.entity.Appointment;
import com.eidiko.entity.Owner;
import com.eidiko.entity.Pet;
import com.eidiko.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final PetService petService;
    private final OwnerService ownerService;
    private final AppointmentRepository appointmentRepo;
    private final ModelMapper modelMapper;

    public void saveAppointment(AppointmentDTO appointmentDTO) {
        Pet pet = petService.getById(appointmentDTO.getPetId())
                .orElseThrow(() -> new RuntimeException("Pet doesn't exists"));
        Owner owner = ownerService.getOwner(appointmentDTO.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner doesn't exists"));
        appointmentDTO.setStatus("Pending");
        Appointment newAppointment = modelMapper.map(appointmentDTO, Appointment.class);
        newAppointment.setPet(pet);
        newAppointment.setOwner(owner);
        appointmentRepo.save(newAppointment);
    }

    public List<AppointmentDTO> getAllAppointments(Long ownerId) {
        List<Appointment> appointments = appointmentRepo.findAllByOwnerId(ownerId);
        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();
        for (Appointment appointment : appointments) {
            AppointmentDTO appointmentDTO = modelMapper.map(appointment, AppointmentDTO.class);
            appointmentDTOS.add(appointmentDTO);
        }
        return appointmentDTOS;
    }
}
