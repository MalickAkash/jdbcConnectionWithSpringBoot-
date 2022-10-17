package com.example.demoJdbcConnectionFinalSprinBootProject.Service;

import com.example.demoJdbcConnectionFinalSprinBootProject.Entity.EmployeeEntity;
import com.example.demoJdbcConnectionFinalSprinBootProject.Repository.EmployeeRepository;
import com.example.demoJdbcConnectionFinalSprinBootProject.Repository.RowMapperImplement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@EnableScheduling
@Service
public class EmployeeService implements EmployeeRepository {

    Logger log= LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Override
    public int saveData(EmployeeEntity employee) {
       String sql="insert into employee_entity values(?,?,?)"; //**order is followed by Data base Table wise.
       return jdbcTemplate.update(sql,employee.getEmpId(),employee.getEmpAge(),employee.getEmpName());
    }

    @Override
    public int updateDataAge(Long empId,Long empAge) {
        String sql="update employee_entity set emp_age=? where emp_id=?";
        return jdbcTemplate.update(sql,empAge,empId);
    }

    @Override
    public int deleteData(Long empId) {
        String sql="delete from employee_entity where emp_id=?";
        return jdbcTemplate.update(sql,empId);
    }

    @Override
    public Optional<EmployeeEntity> fetchOnlyData(String empName) throws RuntimeException{
        if (StringUtils.hasText(empName)) {
            String sql = "select * from employee_entity where emp_name=?";
            RowMapper<EmployeeEntity> rowMpper = new RowMapperImplement();
            try {
                EmployeeEntity employee = jdbcTemplate.queryForObject(sql, rowMpper, empName); //**Jdbc In-build  method for only Data fetch.
                if (employee != null) {
                    return Optional.of(employee);
                }
                if(employee.toString().isEmpty()){
                    throw new Exception();
                }
            }
            catch(Exception e){
                System.out.println(empName+" name doesn't match inside the data base.");
               return null;
            }
        }
        System.out.println("Null value has given input as Employee Name.");
        return null;
    }

    @Override
    public List<EmployeeEntity> fetchAllData() {
        String sql="select * from employee_entity";
        //RowMapper<EmployeeEntity> rowMapper=new RowMapperImplement();
       // List<EmployeeEntity> employeeEntityList=jdbcTemplate.query(sql,rowMapper);
        /* Above two lines are same as below two lines */
        List<EmployeeEntity> employeeEntityList=jdbcTemplate.query(sql,new RowMapperImplement()); //**Jdbc In-build  method for list of all Data fetch.
        return employeeEntityList;
    }




    /* Configure Corn Scheduling */
    @Scheduled(cron="0/10 * * * * *")
    public void schedule1(){
        System.out.println("Corn Scheduling Start after 10 second:");
        log.info("Current time and Date: "+ new Date());
        log.info(jdbcTemplate.getClass().getName());
        log.info(dataSource.getClass().getName());
    }
}
