package eu.accesa.learningplatform.authentication;

import java.util.Properties;

public interface Configuration {

    boolean isEmpty();

    boolean containsKey(String key);

    Object getProperty(String key);

    void addProperty(String key, String value);

    String getPropertyAsString(String key);

    Integer getPropertyAsInteger(String key);

    void setProperties(Properties prop);

    void cleanProperties();
    
}
