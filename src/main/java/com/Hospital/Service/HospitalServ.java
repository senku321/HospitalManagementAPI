package com.Hospital.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.Hospital.Entity.Doctor;
import com.Hospital.Entity.Patient;
import com.Hospital.Exception.DoctorNotFoundException;
import com.Hospital.Exception.PatientNotFoundException;
import com.Hospital.Repository.DocRepository;
import com.Hospital.Repository.PatientRepository;
import com.Hospital.dto.DoctorDto;
import com.Hospital.dto.PatientDto;

@Service

public class HospitalServ {
	
	@Autowired
	DocRepository Drepo;
	
	@Autowired
	PatientRepository Prepo;
	
	
    private PatientDto convertToPatientDto(Patient patient) {
        PatientDto patientDto = new PatientDto();
        patientDto.setName(patient.getName());
        patientDto.setAge(patient.getAge());
        patientDto.setBloodgroup(patient.getBloodgroup());
        patientDto.setGender(patient.getGender());
        patientDto.setHeight(patient.getHeight());
        patientDto.setWeight(patient.getWeight());
        patientDto.setPno(patient.getPno());
        patientDto.setDoctor(patient.getDoctor());
        
        return patientDto;
    }
    
    private DoctorDto convertToDoctorDto(Doctor doctor) {
    	DoctorDto doctorDto = new DoctorDto();
    	doctorDto.setName(doctor.getName());
    	doctorDto.setPno(doctor.getPno());
    	doctorDto.setSpec(doctor.getSpec());
    	doctorDto.setPatients(doctor.getPatients());
    	
    	return doctorDto;
    	
    }
	
	public Doctor AssignPatient(int id, String name)throws PatientNotFoundException,DoctorNotFoundException {
		Patient P= Prepo.findById(id).orElseThrow(() -> new PatientNotFoundException("Invalid Patient Id"));
		Doctor D = Drepo.findByName(name).orElseThrow(() -> new DoctorNotFoundException("Invalid Doctor Name"));
		P.setDoctor(D);
		D.getPatients().add(P);
		Drepo.save(D);
		Prepo.save(P);
		return D;
		
		}
	
	public List<DoctorDto> findAllDoctors() {
		
		List<DoctorDto> doctorData = Drepo.findAll().stream().map(this::convertToDoctorDto).collect(Collectors.toList());
		
		 return doctorData;
		}
	
	public List<PatientDto> findAllPatients() {
		
		List<PatientDto> patientData = Prepo.findAll().stream().map(this::convertToPatientDto).collect(Collectors.toList());
		 return patientData;
		}
	
	public ResponseEntity<Doctor> AddDoctor(Doctor d) {
        try {
            Doctor savedDoctor = Drepo.save(d);

            return ResponseEntity.ok(savedDoctor);
        } catch (Exception e) {
        	System.out.println("exception=" + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
	
	public ResponseEntity<Patient> addpatient(Patient p) {
        try {
            Patient savedPatient = Prepo.save(p);

            return ResponseEntity.ok(savedPatient);
        } catch (Exception e) {
        	System.out.println("exception=" + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
	
	public Patient findPatientByName(String name)throws PatientNotFoundException {
		
		Patient P = Prepo.findByName(name);
		if(P!=null) return P;
		else throw new PatientNotFoundException("No patient of this name registered");
	}
	
	public Doctor findAssignedDoctor(int id) throws DoctorNotFoundException, PatientNotFoundException {
		Patient P = Prepo.findById(id).orElseThrow(()-> new PatientNotFoundException("Invalid Patient Id"));
		Doctor D = P.getDoctor();
		if(D!=null) return D;
		else throw new DoctorNotFoundException("No doctor is Assigned to this pateint yet");
		
	}
	
	
	public Optional<Doctor> findDocByName(String name) {
		return Drepo.findByName(name);
	}
}
