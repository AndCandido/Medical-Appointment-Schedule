package io.github.AndCandido.medicalappointmentschedule.web.controllers;

import io.github.AndCandido.medicalappointmentschedule.domain.mappers.PatientMapper;
import io.github.AndCandido.medicalappointmentschedule.domain.models.dtos.request.PatientRequestDto;
import io.github.AndCandido.medicalappointmentschedule.domain.models.dtos.response.PatientResponseDto;
import io.github.AndCandido.medicalappointmentschedule.domain.models.entities.Patient;
import io.github.AndCandido.medicalappointmentschedule.domain.services.IPatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
public record PatientController(
    IPatientService patientService
) {

    @PostMapping
    public ResponseEntity<PatientResponseDto> savePatient(
        @RequestBody @Valid PatientRequestDto patientRequestDto
    ) {
        Patient patientSaved = patientService.savePatient(patientRequestDto);
        PatientResponseDto patientResponseDto = PatientMapper.toResponseDto(patientSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        List<PatientResponseDto> patientResponseDtoList = PatientMapper.toResponseDtoList(patients);
        return ResponseEntity.ok(patientResponseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDto> getPatientById(@PathVariable UUID id) {
        Patient patient = patientService.getPatientById(id);
        PatientResponseDto patientResponseDto = PatientMapper.toResponseDto(patient);
        return ResponseEntity.ok(patientResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDto> updatePatientById(
        @PathVariable UUID id,
        @RequestBody @Valid PatientRequestDto patientRequestDto
    ) {
        Patient patient = patientService.updatePatientById(id, patientRequestDto);
        PatientResponseDto patientResponseDto = PatientMapper.toResponseDto(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatientById(@PathVariable UUID id) {
        patientService.deletePatientById(id);
        return ResponseEntity.noContent().build();
    }
}
