package com.ashwin.java;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DropwizardHibernateConfiguration extends Configuration {
    private String url;

    private DataSourceFactory dataSourceFactory = new DataSourceFactory();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(final DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }
}
