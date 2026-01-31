package com.codeWithProject.employee.service;


import com.codeWithProject.employee.entity.Employee;
import com.codeWithProject.employee.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    // 1.To Create  All Employee Details
    public Employee postEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    // 2.To Get All Employee Details
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    //3.To Delete Employee Detail
    public void  deleteEmployee(Long id){
        if (!employeeRepository.existsById(id)){
    throw new EntityNotFoundException("Employee With Id " + id + "not found");}
    employeeRepository.deleteById(id);
        }
   //4. To Get Employee by EmployeeID

    public Employee getEmployeeById(Long id){
        return  employeeRepository.findById(id).orElse(null);
    }

    public Employee updateEmployee(Long id,Employee employee){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()){
            Employee existingEmployee = optionalEmployee.get();

            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setName(employee.getName());
            existingEmployee.setPhone(employee.getPhone());
            existingEmployee.setDepartment(employee.getDepartment());

            return employeeRepository.save(existingEmployee);
        }
        return null;
    }

}
