package com.Hospital.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hospital.Entity.Doctor;
import com.Hospital.Entity.Patient;
import com.Hospital.Exception.DoctorNotFoundException;
import com.Hospital.Exception.PatientNotFoundException;
import com.Hospital.Repository.DocRepository;
import com.Hospital.Repository.PatientRepository;
import com.Hospital.Service.HospitalServ;
import com.Hospital.dto.DoctorDto;
import com.Hospital.dto.PatientDto;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/hospital")
public class HospitalController {
	
	@Autowired
	HospitalServ Serv;
	
	@PostMapping("/patient/add")
	private ResponseEntity<Patient> addpatient(@RequestBody @Valid Patient p) {
		return Serv.addpatient(p);
	}
	
	@PostMapping("doctor/add")
	private ResponseEntity<Doctor> AddDoctor(@RequestBody @Valid Doctor d) {
		return Serv.AddDoctor(d);
	}
	
	@PutMapping("{name}/{pid}")
	private Doctor AssignPatient(@PathVariable int pid,@PathVariable String name) throws PatientNotFoundException, DoctorNotFoundException {
		return Serv.AssignPatient(pid, name);
		
	}
	@GetMapping("doctor/all")
	private List<DoctorDto> findAllDoctors() {
		 return Serv.findAllDoctors();
		}
	@GetMapping("patient/all")
	private List<PatientDto> findAllPatients() {
		 return Serv.findAllPatients();
		}
	
	@GetMapping("patient/{name}")
	private Patient findPatientByName(@PathVariable String name) throws PatientNotFoundException {
		return Serv.findPatientByName(name);
	}
	
	@GetMapping("assigneddoctor/{id}")
	private Doctor findAssignedDoctor(@PathVariable int id) throws DoctorNotFoundException, PatientNotFoundException {
		return Serv.findAssignedDoctor(id);
	}
	
	@GetMapping("doctor/{name}")
	public Optional<Doctor> findDocByName(@PathVariable String name){
		return Serv.findDocByName(name);
	}
}
