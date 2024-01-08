package io.github.AndCandido.medicalappointmentschedule.domain.services;

import io.github.AndCandido.medicalappointmentschedule.domain.models.dtos.request.AppointmentRequestDto;
import io.github.AndCandido.medicalappointmentschedule.domain.models.entities.Appointment;

import java.util.List;
import java.util.UUID;

public interface IAppointmentService {

    Appointment saveAppointment(AppointmentRequestDto appointmentRequestDto);

    List<Appointment> getAllAppointment();

    Appointment getAppointmentById(UUID id);

    Appointment updateAppointmentById(UUID id, AppointmentRequestDto appointmentRequestDto);

    void deleteAppointmentById(UUID id);
}
