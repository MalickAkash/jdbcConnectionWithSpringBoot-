package com.example.demoJdbcConnectionFinalSprinBootProject.Repository;

import com.example.demoJdbcConnectionFinalSprinBootProject.Entity.EmployeeEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    int saveData(EmployeeEntity employee);

    int updateDataAge(Long empId,Long empAge);

    int deleteData(Long empId);

    Optional<EmployeeEntity> fetchOnlyData(String empName);

    List<EmployeeEntity> fetchAllData();
}
