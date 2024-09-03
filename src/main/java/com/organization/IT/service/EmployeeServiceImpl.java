package com.organization.IT.service;

import com.organization.IT.entity.Employee;
import com.organization.IT.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Employee findById(int id) {

        Optional<Employee> result = employeeRepository.findById(id);
        Employee theEmployee = null;
        if (result.isPresent()){
            theEmployee = result.get();
        }
        else {
            throw new RuntimeException("Employee not found with id: "+id);
        }
        return theEmployee;
    }

    @Override
    public Employee save(Employee employee) {

        return employeeRepository.save(employee);

    }

    @Override
    public void deleteById(int id){
        employeeRepository.deleteById(id);
    }
}
