package Hospital.Management.Testing;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Hospital.Management.Model.Doctor;
import Hospital.Management.Model.Patient;
import Hospital.Management.Repository.DoctorRepository;
import Hospital.Management.Repository.PatientRepository;
import Hospital.Management.Service.Impl.DoctorServiceImpl;
import Hospital.Management.Service.Impl.PatientServiceImpl;
@RunWith(SpringRunner.class)
@SpringBootTest

class HospitalManagementApplicationTest {	
	
		@Nested
		class DoctorTest{
			@Autowired
			private DoctorServiceImpl service;
			@MockBean
			private DoctorRepository repository;
		    
		    @Test
		    public void  getAllDoctorsTest(){
		    	when(repository.findAll()).thenReturn(Stream.of
		    			(new Doctor(1,"kalyan",43,"Male","Surgery"),
		    			new Doctor(2,"Raj",23,"Male","Surgery")).collect(Collectors.toList()));
		    	assertEquals(2, service.getAllDoctors().size());
		    }		    
		    @Test 
		    public void createDoctorTest() {
		    	Doctor doctor = new Doctor(1,"kalyan",43,"Male","Surgery");
		    	when(repository.save(doctor)).thenReturn(doctor);
		    	assertEquals(doctor, service.createDoctor(doctor));		    	
		    }
		    @Test 
		    public void getdoctorByIdTest() {
		    	Doctor doctor = repository.save(new Doctor(1,"kalyan",43,"Male","Surgery"));
		    	Doctor expectedDoctor = repository.getById(1);
		    	assertEquals(doctor, expectedDoctor);		    	
		    }
		    @Test
		    public void deleteDoctorTest() {
		    	Doctor doctor = new Doctor(1,"kalyan",43,"Male","Surgery");
		    	when(repository.save(doctor)).thenReturn(doctor);
		    	repository.delete(doctor);
		    	assertEquals(0,service.getAllDoctors().size());	
		    }
		    @Test
		    public void updateDoctorTest(){
		    	Doctor doctor = new Doctor(1,"kalyan",43,"Male","Surgery");
		    	when(repository.save(doctor)).thenReturn(doctor);
		    	doctor.setDoctor_name("Raj");
		    	Doctor updatedDoctor = repository.save(doctor);
		    	Assertions.assertThat(updatedDoctor.getDoctor_name()).isEqualTo("Raj");		    	
		    }
		}
		@Nested
		class PatientTest{
			@Autowired
			private PatientServiceImpl service;
			@MockBean
			private PatientRepository repository;
		    
		    @Test
		    public void  getAllPatientsTest(){
		    	when(repository.findAll()).thenReturn(Stream.of
		    			(new Patient(1,"Aarthy","kalyan",new Date()),
		    			new Patient(2,"Abarna","Raj",new Date())).collect(Collectors.toList()));
		    	assertEquals(2, service.getAllPatients().size());
		    }
		    @Test 
		    public void getpatientByIdTest() {
		    	Patient patient = repository.save(new Patient(1,"Aarthy","kalyan",new Date()));
		    	Patient expectedPatient = repository.getById(1);
		    	assertEquals(patient, expectedPatient);		    	
		    }
		    @Test 
		    public void createPatientTest() {
		    	Patient patient = new Patient(1,"Aarthy","kalyan",new Date());
		    	when(repository.save(patient)).thenReturn(patient);
		    	assertEquals(patient, service.createPatient(patient));		    	
		    }
		    @Test
		    public void deletePatientTest() {
		    	Patient patient = new Patient(1,"Aarthy","kalyan",new Date());
		    	when(repository.save(patient)).thenReturn(patient);	
		    	repository.delete(patient);
		    	assertEquals(0,service.getAllPatients().size());	
		    }
		    @Test
		    public void updateDoctorTest(){
		    	Patient patient = new Patient(1,"Aarthy","kalyan",new Date());
		    	when(repository.save(patient)).thenReturn(patient);	
		    	patient.setPatient_name("joyce");
		    	Patient updatedPatient = repository.save(patient);
		    	Assertions.assertThat(updatedPatient.getPatient_name()).isEqualTo("joyce");		    	
		    }
		}
	
}
