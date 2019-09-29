package com.example.monitoring.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Endpoint(id = "my")
@Component
public class MyEndpoint {

    private String value = "Hello world";

    @ReadOperation
    public String getValue() {
        return this.value;
    }

    @WriteOperation
    public void setValue(String value) {
        this.value = value;
    }

    @DeleteOperation
    public void deleteValue() {
        this.value = "Hello world";
    }
}
