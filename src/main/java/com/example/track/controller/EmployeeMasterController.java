package com.example.track.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.track.entity.EmployeeMaster;
import com.example.track.repository.DepartmentRepo;
import com.example.track.repository.ProjectRepo;
import com.example.track.service.EmployeeMasterService;
import com.example.track.vo.EmployeeMasterRequest;
import com.example.track.vo.GenericResponse;

@CrossOrigin
@RestController
@RequestMapping("/track")
public class EmployeeMasterController {
	
	@Autowired
	private EmployeeMasterService employeeMasterService;
	
	@Autowired 
	private DepartmentRepo departmentRepo;
	
	@Autowired
	private ProjectRepo projectRepo;
	
   //register Employee
	@PostMapping("/addEmployee")
	public ResponseEntity<?> addEmployee(@RequestBody EmployeeMasterRequest employeeMasterRequest) throws Exception {	
		GenericResponse response = null;
		response = employeeMasterService.registerEmployee(employeeMasterRequest);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//get All Employees emp
	@GetMapping("/getallEmployee")
	public List<EmployeeMaster> getAllEmp(){
		
		return employeeMasterService.getAllEmp();
	}

	//Get Employee By EmployeeCode
	@GetMapping("/getEmployee/{empCode}")
	public EmployeeMaster getEmpByCode(@PathVariable String empCode) {		
		return employeeMasterService.getEmpByCode(empCode);
	}
	
	//Update Employee
	@PutMapping("/update/{empCode}")
	public ResponseEntity<?> updateEmployee(@PathVariable String empCode, @RequestBody EmployeeMasterRequest employeeMasterRequest) throws Exception{
		GenericResponse response = null;
		response = employeeMasterService.updateEmployee(employeeMasterRequest);
		
		return new ResponseEntity<> (response, HttpStatus.OK);
	}
	
	//Delete Employee
	@DeleteMapping("/deleteEmployee/{empCode}")
    public ResponseEntity<String> deleteEmployeeByCode(@PathVariable String empCode) {
        employeeMasterService.deleteEmployeeByCode(empCode);
        return ResponseEntity.ok("Employee deleted successfully");
    }
	
    //Department List //get all department 
	@GetMapping("/departments")
	public ResponseEntity<List<Map<String, String>>> getDepartmentNames() {
	    List<Map<String, String>> departmentNames = departmentRepo.findAllDepartmentNames();
	    return ResponseEntity.ok(departmentNames);
	}

	//get all projects
		@GetMapping("/projects")
		public ResponseEntity<List<Map<String, String>>> getProjectNames(){
			List<Map<String, String>> projectNames = projectRepo.findAllProjectNames();
			return ResponseEntity.ok(projectNames);
		}
}
