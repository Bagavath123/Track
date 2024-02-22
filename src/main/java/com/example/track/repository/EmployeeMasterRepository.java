package com.example.track.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.track.entity.EmployeeMaster;

@Repository
public interface EmployeeMasterRepository extends JpaRepository<EmployeeMaster, Long> {
	
    EmployeeMaster save (EmployeeMaster newEmployee);
    
    EmployeeMaster findByEmpCode(String employeeCode);
    

   
	

}
