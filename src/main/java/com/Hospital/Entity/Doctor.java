package com.Hospital.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Did;
	@NotNull(message = "name cannot be null")
	private String name;
	@Column(name = "specialization")
	private String spec;
	@Column(name = "phone number")
	@Pattern(regexp="^\\d{10}$",message="Mobile have 10 digit")
	private String pno;
	@JsonIgnore
	@OneToMany(mappedBy = "doctor" ,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Patient> patients;
}
