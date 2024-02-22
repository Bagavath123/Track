package com.example.track.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.track.entity.Project;

public interface ProjectRepo  extends JpaRepository<Project, Long>{

	//Project findByProjectName(String projectName);

   // Project save(Project project);
    
    @Query(nativeQuery = true, value = "Select project from tbl_project")
       List<Map<String, String>> findAllProjectNames(); 

}
