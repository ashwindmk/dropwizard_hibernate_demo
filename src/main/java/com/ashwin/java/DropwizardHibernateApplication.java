package com.ashwin.java;

import com.ashwin.java.dao.EmployeeDao;
import com.ashwin.java.model.Employee;
import com.ashwin.java.resource.EmployeeResource;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DropwizardHibernateApplication extends Application<DropwizardHibernateConfiguration> {
    private HibernateBundle<DropwizardHibernateConfiguration> hibernate = new HibernateBundle<DropwizardHibernateConfiguration>(Employee.class) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(DropwizardHibernateConfiguration config) {
            return config.getDataSourceFactory();
        }
    };

    public static void main(String[] args) throws Exception {
        new DropwizardHibernateApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard-hibernate-demo";
    }

    @Override
    public void initialize(Bootstrap<DropwizardHibernateConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(DropwizardHibernateConfiguration config, Environment env) throws Exception {
        EmployeeDao employeeDao = new EmployeeDao(hibernate.getSessionFactory());
        final EmployeeResource employeeResource = new EmployeeResource(employeeDao);
        env.jersey().register(employeeResource);
    }
}
