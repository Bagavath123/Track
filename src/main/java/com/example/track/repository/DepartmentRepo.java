package com.example.track.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.track.entity.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long>{
	 
	 @Query(nativeQuery = true, value = "SELECT department FROM tbl_department")
	    List<Map<String, String>> findAllDepartmentNames();
}
