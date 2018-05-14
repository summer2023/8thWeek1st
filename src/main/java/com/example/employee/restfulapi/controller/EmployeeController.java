package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employee")
    public @ResponseBody Iterable<Employee> findAllEmployees(){return employeeRepository.findAll();}

    @GetMapping(value = "/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeRepository.findById(id);
    }

    @GetMapping(value = "/page/{page}/pageSize/{pageSize}")
    public Page<Employee> getEmployees(@PathVariable int page, @PathVariable int pageSize) {
        return employeeRepository.findAll(new PageRequest(page, pageSize));
    }

    @GetMapping(value = "/male")
    public List<Employee> getMaleEmployee(){return employeeRepository.findByGender("male");}

    @PostMapping
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping(value = "/{id}")
    public Employee updateEmployee(@PathVariable Long id, @ModelAttribute Employee employee) {
        employeeRepository.updateById(id, employee.getName(), employee.getAge(), employee.getGender(), employee.getSalary(), employee.getCompanyId());
        return employeeRepository.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }
}
