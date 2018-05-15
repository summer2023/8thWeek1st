package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping
    public @ResponseBody Iterable<Company> findAllCompanys(){return companyRepository.findAll();}

    @GetMapping(value = "/{id}")
    public Company getCompany(@PathVariable Integer id) {
        return companyRepository.findById(id);
    }

    @GetMapping(value = "/{id}/employees")
    public List<Employee> getEmployees(@PathVariable Integer id) {
        return companyRepository.findEmployeeByCompanyId(id);
    }

    @GetMapping(value = "/page/{page}/pageSize/{pageSize}")
    public Page<Company> getCompanys(@PathVariable int page, @PathVariable int pageSize) {
        return companyRepository.findAll(new PageRequest(page, pageSize));
    }

    @PostMapping
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    @PutMapping(value = "/{id}")
    public Company updateCompany(@PathVariable Integer id, @ModelAttribute Company company) {
        companyRepository.updateById(id,company.getCompanyName(),company.getEmployeesNumber());
        return companyRepository.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        companyRepository.deleteById(id);
    }
}
