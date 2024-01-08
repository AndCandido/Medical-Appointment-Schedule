package io.github.AndCandido.medicalappointmentschedule.web.controllers;

import io.github.AndCandido.medicalappointmentschedule.domain.mappers.AppointmentMapper;
import io.github.AndCandido.medicalappointmentschedule.domain.models.dtos.request.AppointmentRequestDto;
import io.github.AndCandido.medicalappointmentschedule.domain.models.dtos.response.AppointmentResponseDto;
import io.github.AndCandido.medicalappointmentschedule.domain.models.entities.Appointment;
import io.github.AndCandido.medicalappointmentschedule.domain.services.IAppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/appointments")
public record AppointmentController(
    IAppointmentService appointmentService
) {
    @PostMapping
    public ResponseEntity<AppointmentResponseDto> saveAppointment(
        @RequestBody @Valid AppointmentRequestDto appointmentRequestDto
    ) {
        Appointment appointment = appointmentService.saveAppointment(appointmentRequestDto);
        AppointmentResponseDto appointmentResponseDto =
            AppointmentMapper.toResponseDto(appointment);

        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointments() {
        List<Appointment> appointmentList = appointmentService.getAllAppointment();
        List<AppointmentResponseDto> appointmentResponseDtoList =
            AppointmentMapper.toResponseDtoList(appointmentList);

        return ResponseEntity.ok(appointmentResponseDtoList);
    }

    @GetMapping("{id}")
    public ResponseEntity<AppointmentResponseDto> getAppointmentById(@PathVariable UUID id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        AppointmentResponseDto appointmentResponseDto = AppointmentMapper.toResponseDto(appointment);
        return ResponseEntity.ok(appointmentResponseDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<AppointmentResponseDto> updateAppointmentById(
        @PathVariable UUID id,
        @RequestBody AppointmentRequestDto appointmentRequestDto
    ) {
        Appointment appointment = appointmentService.updateAppointmentById(id, appointmentRequestDto);
        AppointmentResponseDto appointmentResponseDto = AppointmentMapper.toResponseDto(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentResponseDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAppointmentById(@PathVariable UUID id) {
        appointmentService.deleteAppointmentById(id);
        return ResponseEntity.noContent().build();
    }
}
