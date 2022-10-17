package com.example.demoJdbcConnectionFinalSprinBootProject.Controller;

import com.example.demoJdbcConnectionFinalSprinBootProject.Entity.EmployeeEntity;
import com.example.demoJdbcConnectionFinalSprinBootProject.Service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController implements CommandLineRunner {

    Logger log= LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @Override
    public void run(String... args) throws Exception ,IllegalStateException{
        /* insert data inside the Data base */
        //**order is followed by Employee entity-class wise.
        employeeService.saveData(new EmployeeEntity(1L,"Akash",25L));
        employeeService.saveData(new EmployeeEntity(2L,"Dip",50L));
        employeeService.saveData(new EmployeeEntity(3L,"Rupam",30L));
        employeeService.saveData(new EmployeeEntity(4L,"Anupal",20L));
        System.out.println("All Data are stored inside the DataBase.");

        /* update data to the data base */
        employeeService.updateDataAge(2L,65L);
        System.out.println("Update done.");

        /* delete specific data from data base find by empId */
        employeeService.deleteData(3L);
        System.out.println("Delete row successfully.");

        /* This method for single row fetch */
        Optional<EmployeeEntity> employeeEntity=employeeService.fetchOnlyData("anupal");
        if(employeeEntity != null){
            System.out.println("Data is found: " + employeeEntity);
        }


        List<EmployeeEntity> employeeEntityList=employeeService.fetchAllData();
        if(employeeEntityList.isEmpty()){
            System.out.println("Employee Data Base is empty.");
        }
        else{
            System.out.println("Employee List:");
            for(EmployeeEntity e : employeeEntityList) {
                /* Same things multi times print only Approach is different */
                log.info("Employee: "+e.toString());
                log.info("EmployeeEntity :"+ e);
                log.info("EmployeeEntity : {}",e);
            }
        }

    }

}