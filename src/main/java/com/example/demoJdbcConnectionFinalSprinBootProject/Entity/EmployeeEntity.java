package com.example.demoJdbcConnectionFinalSprinBootProject.Entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EmployeeEntity {
    @Id   /* only mention primary id, does not need to do auto generated primary key */
    private Long empId;
    @NotNull
    private String empName;
    private Long empAge;
}
