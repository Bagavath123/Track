package com.example.track.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.track.entity.Department;
import com.example.track.entity.EmployeeMaster;
import com.example.track.entity.Project;
import com.example.track.repository.DepartmentRepo;
import com.example.track.repository.EmployeeMasterRepository;
import com.example.track.repository.ProjectRepo;
import com.example.track.vo.EmployeeMasterRequest;
import com.example.track.vo.GenericResponse;

import jakarta.persistence.EntityNotFoundException;



@Service
public class EmployeeMasterService {

	@Autowired
	private EmployeeMasterRepository employeeMasterRepository;
	
    @Autowired
    private DepartmentRepo departmentRepo;
    
    @Autowired
    private ProjectRepo projectRepo;
	
	
	//Add new Employee
	public GenericResponse registerEmployee(EmployeeMasterRequest employeeMasterRequest) throws Exception{
		GenericResponse response = new GenericResponse();
		
		try {
			//Check if the Employee Already exits
			EmployeeMaster existingEmp = employeeMasterRepository.findByEmpCode(employeeMasterRequest.getEmpCode());
			if(existingEmp != null) {
				return GenericResponse.getFailureResponse("108", "Employee with this Employee Code Already Exists");
			}
        
			//create a new Employee		
			EmployeeMaster addEmployee = new EmployeeMaster(); 
			addEmployee.setEmpCode(employeeMasterRequest.getEmpCode());
			addEmployee.setEmployeeName(employeeMasterRequest.getEmployeeName());
			addEmployee.setStatus(1);
			addEmployee.setPassword(employeeMasterRequest.getPassword());
			addEmployee.setEmployeeRole("R1");
			addEmployee.setDepartmentName(employeeMasterRequest.getDepartmentName());
			addEmployee.setProjectName(employeeMasterRequest.getProjectName());
			
			//addEmployee.setSystemMacId(employeeMasterRequest.getSystemMacId());	
			//addEmployee.setMobileNumber(employeeMasterRequest.getMobileNumber());
			//addEmployee.setEmployeeEmail(employeeMasterRequest.getEmployeeEmail());
			//addEmployee.setEmpDoj(employeeMasterRequest.getEmpDoj());
			//addEmployee.setDistrictId(employeeMasterRequest.getDistrictId());
			//addEmployee.setReportingTo(employeeMasterRequest.getReportingTo());
			//addEmployee.setCreatedTime(null);
			
			employeeMasterRepository.save(addEmployee);
			
			response.setData(addEmployee);
			response.setStatus("S");
	        response.setUserDisplayMsg("Employee registered Successfully");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return GenericResponse.getFailureResponse("108","Exception in RegisterEmployee: " + e.getMessage());
		}
		return response;	
	}
	
	//get Employee By code
	public EmployeeMaster getEmpByCode(String employeeCode) {		
		return employeeMasterRepository.findByEmpCode(employeeCode);
	}
	
	//Update Employee
	public GenericResponse updateEmployee(EmployeeMasterRequest employeeMasterRequest)throws Exception{
		GenericResponse response = new GenericResponse();
		
		try {
			EmployeeMaster existingEmp = employeeMasterRepository.findByEmpCode(employeeMasterRequest.getEmpCode());
			if(existingEmp != null) {
				existingEmp.setDepartmentName(employeeMasterRequest.getDepartmentName());
				existingEmp.setProjectName(employeeMasterRequest.getProjectName());
				
				employeeMasterRepository.save(existingEmp);
				
				response.setData(existingEmp);
				response.setStatus("S");
				response.setUserDisplayMsg("Employee Updated Sucessfully");	}
			else {
			     return GenericResponse.getFailureResponse("108", "Employee with this employee Code is not found");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			return GenericResponse.getFailureResponse("108", "Exception in Update Employee:" + e.getMessage());
		}
		return response;
	}
	
	//delete Employee
	public void deleteEmployeeByCode(String empCode) {
        EmployeeMaster employee = employeeMasterRepository.findByEmpCode(empCode);
        if (employee != null) {
            employeeMasterRepository.delete(employee);
        } else {
            throw new EntityNotFoundException("Employee with code " + empCode + " not found");
        }
    }

	//get All Employees
	public List<EmployeeMaster> getAllEmp() {
		return employeeMasterRepository.findAll();
	}
	
}
