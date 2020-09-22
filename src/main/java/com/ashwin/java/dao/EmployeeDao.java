package com.ashwin.java.dao;

import com.ashwin.java.model.Employee;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EmployeeDao extends AbstractDAO<Employee> {
    public EmployeeDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Employee> getAll() {
        CriteriaQuery<Employee> criteriaQuery = currentSession().getCriteriaBuilder().createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root);
        Query<Employee> query = currentSession().createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<Employee> getRich() {
        CriteriaBuilder criteriaBuilder = currentSession().getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root).where(criteriaBuilder.gt(root.get("salary"), 250000));
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("salary")));
        Query<Employee> query = currentSession().createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<Employee> getPoor() {
        CriteriaBuilder criteriaBuilder = currentSession().getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root).where(criteriaBuilder.le(root.get("salary"), 250000));
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("salary")));
        Query<Employee> query = currentSession().createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<Employee> getMid() {
        CriteriaBuilder criteriaBuilder = currentSession().getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root).where(criteriaBuilder.between(root.get("salary"), 200000, 400000));
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("salary")));
        Query<Employee> query = currentSession().createQuery(criteriaQuery);
        return query.getResultList();
    }

    public Employee getById(Long id) {
        return currentSession().get(Employee.class, id);
    }

    public Employee insert(Employee employee) {
        return persist(employee);
    }

    public void update(Employee employee) {
        currentSession().saveOrUpdate(employee);
    }

    public void delete(Employee employee) {
        currentSession().delete(employee);
    }
}
