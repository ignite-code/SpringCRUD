package com.organization.IT.controller;

import com.organization.IT.entity.Employee;
import com.organization.IT.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listOfEmployees(Model model){
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "/employees/list-employees";
    }

    @GetMapping("/showFormToAdd")
    public String showFormToAdd(Model model){
        Employee employee  = new Employee();
        model.addAttribute("employee", employee);
        return "/employees/employee-form";
    }

   @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee ){
        employeeService.save(employee);
        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String updateEmployee(@RequestParam("employeeId") int id, Model model){

        Employee employee = employeeService.findById(id);
        model.addAttribute("employee",employee);

        return "/employees/employee-form";
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("employeeId") int id){
        employeeService.deleteById(id);
        return "redirect:/employees/list";
    }

}
