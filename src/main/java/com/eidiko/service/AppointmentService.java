package com.eidiko.service;

import com.eidiko.dto.AppointmentDTO;
import com.eidiko.entity.Appointment;
import com.eidiko.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepo;
    private final ModelMapper modelMapper;

    public void saveAppointment(AppointmentDTO appointmentDTO) {
        Appointment newAppointment = modelMapper.map(appointmentDTO, Appointment.class);
        Appointment savedAppointment = appointmentRepo.save(newAppointment);
    }

    public Object getAllAppointments() {
        return appointmentRepo.findAll();
    }
}
