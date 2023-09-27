package com.Hospital.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pid;
	@NotNull(message = "name cannot be null")
	private String name;
	private int age;
	private String gender;
	@Column(name = "Phone_Number")
	@Pattern(regexp="^\\d{10}$",message="Mobile have 10 digit")
	private String pno;
	private int weight;
	private int height;
	@Pattern(regexp = "(A|B|AB|O)[+-]",message = "not a valid blood group")
	@Column(name = "Blood_group")
	private String bloodgroup;
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "doctor_id")
	@JsonIgnore
	private Doctor doctor; 
	
}
