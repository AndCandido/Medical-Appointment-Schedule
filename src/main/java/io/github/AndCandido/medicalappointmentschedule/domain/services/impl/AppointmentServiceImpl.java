package io.github.AndCandido.medicalappointmentschedule.domain.services.impl;

import io.github.AndCandido.medicalappointmentschedule.api.exceptions.ResourceNotFound;
import io.github.AndCandido.medicalappointmentschedule.domain.models.dtos.request.AppointmentRequestDto;
import io.github.AndCandido.medicalappointmentschedule.domain.models.entities.Appointment;
import io.github.AndCandido.medicalappointmentschedule.domain.models.entities.Doctor;
import io.github.AndCandido.medicalappointmentschedule.domain.models.entities.Patient;
import io.github.AndCandido.medicalappointmentschedule.domain.models.repositories.AppointmentRepository;
import io.github.AndCandido.medicalappointmentschedule.domain.services.IAppointmentService;
import io.github.AndCandido.medicalappointmentschedule.domain.services.IDoctorService;
import io.github.AndCandido.medicalappointmentschedule.domain.services.IPatientService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final IDoctorService doctorService;
    private final IPatientService patientService;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, IDoctorService doctorService, IPatientService patientService) {
        this.appointmentRepository = appointmentRepository;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @Override
    @Transactional
    public Appointment saveAppointment(AppointmentRequestDto appointmentRequestDto) {
        Doctor doctor = doctorService.getDoctorById(appointmentRequestDto.doctorId());
        Patient patient = patientService.getPatientById(appointmentRequestDto.patientId());

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        BeanUtils.copyProperties(appointmentRequestDto, appointment);

        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAllAppointment() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment getAppointmentById(UUID id) {
        return appointmentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFound("Appointment Not Found"));
    }

    @Override
    @Transactional
    public Appointment updateAppointmentById(UUID id, AppointmentRequestDto appointmentRequestDto) {
        Appointment appointment = getAppointmentById(id);
        BeanUtils.copyProperties(appointmentRequestDto, appointment);

        Patient patientOnAppointmentSaved = appointment.getPatient();
        UUID idPatientRequest = appointmentRequestDto.patientId();

        if(patientOnAppointmentSaved == null || patientOnAppointmentSaved.getId().equals(idPatientRequest)) {
            Patient patient = patientService.getPatientById(idPatientRequest);
            appointment.setPatient(patient);
        }

        Doctor doctorOnAppointmentSaved = appointment.getDoctor();
        UUID idDoctorRequest = appointmentRequestDto.doctorId();

        if(doctorOnAppointmentSaved == null || doctorOnAppointmentSaved.getId().equals(idDoctorRequest)) {
            Doctor doctor = doctorService.getDoctorById(appointmentRequestDto.doctorId());
            appointment.setDoctor(doctor);
        }

        return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointmentById(UUID id) {
        Appointment appointment = getAppointmentById(id);
        appointmentRepository.delete(appointment);
    }
}
