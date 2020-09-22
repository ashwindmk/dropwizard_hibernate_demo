package com.ashwin.java.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee")
/*@NamedQueries({
        @NamedQuery(name = "demo.model.Employee.getAll", query = "SELECT e FROM employee e")
})*/
public class Employee {
    @Id
    @Column(name = "id", nullable = false)
    @NotNull
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "department")
    private String department;

    @Column(name = "salary")
    private Long salary;

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
