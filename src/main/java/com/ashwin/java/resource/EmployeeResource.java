package com.ashwin.java.resource;

import com.ashwin.java.dao.EmployeeDao;
import com.ashwin.java.model.Employee;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/v1/employee")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {
    private EmployeeDao employeeDao;

    public EmployeeResource(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @GET
    @Timed
    @Path("/ping")
    public String ping() {
        return "Pong";
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("/")
    public List<Employee> getAll(@QueryParam("class") String status) {
        if ("rich".equalsIgnoreCase(status)) {
            return employeeDao.getRich();
        } else if ("poor".equalsIgnoreCase(status)) {
            return employeeDao.getPoor();
        } else if ("middle".equalsIgnoreCase(status)) {
            return employeeDao.getMid();
        } else {
            return employeeDao.getAll();
        }
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("/{id}")
    public Employee getById(@PathParam("id") Long id) {
        return employeeDao.getById(id);
    }

    @POST
    @Timed
    @UnitOfWork
    @Path("/")
    public Employee add(@Valid Employee employee) {
        return employeeDao.insert(employee);
    }

    @PUT
    @Timed
    @UnitOfWork
    @Path("/{id}")
    public Employee update(@PathParam("id") Long id, @Valid Employee employee) {
        employee.setId(id);
        return employeeDao.insert(employee);
    }

    @DELETE
    @Timed
    @UnitOfWork
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        employeeDao.delete(employeeDao.getById(id));
    }
}
