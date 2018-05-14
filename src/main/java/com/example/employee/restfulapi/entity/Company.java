package com.example.employee. restfulapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Company {
        @Id
        @GeneratedValue
        private Integer id;

        @Column(nullable = false)
        private String companyName;

        @Column
        private String employeesNumber;

        public Company() {
        }

        public Company(Integer id, String companyName,String employeesNumber ) {
            this.id = id;
            this.companyName = companyName;
            this.employeesNumber = employeesNumber;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getEmployeesNumber() {
            return employeesNumber;
        }

        public void setEmployeesNumber(String employeesNumber) {
            this.employeesNumber = employeesNumber;
        }
    }
