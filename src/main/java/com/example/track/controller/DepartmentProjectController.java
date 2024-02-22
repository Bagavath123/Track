//package com.example.track.controller;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.track.repository.DepartmentRepo;
//import com.example.track.repository.ProjectRepo;
//
//@CrossOrigin
//@RestController
//@RequestMapping("/track")
//
//public class DepartmentProjectController {
//
//	@Autowired 
//	private DepartmentRepo departmentRepo;
//	
//	@Autowired
//	private ProjectRepo projectRepo;
//	
//	
//	//get all department 
//	@GetMapping("/departments")
//	public ResponseEntity<List<Map<String, String>>> getDepartmentNames() {
//	    List<Map<String, String>> departmentNames = departmentRepo.findAllDepartmentNames();
//	    return ResponseEntity.ok(departmentNames);
//	}
//	
//	//get all projects
//	@GetMapping("/projects")
//	public ResponseEntity<List<Map<String, String>>> getProjectNames(){
//		List<Map<String, String>> projectNames = projectRepo.findAllProjectNames();
//		return ResponseEntity.ok(projectNames);
//	}
//	
//}
