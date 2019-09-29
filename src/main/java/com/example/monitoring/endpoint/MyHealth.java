package com.example.monitoring.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyHealth implements HealthIndicator {

    @Autowired
    MyEndpoint myEndpoint;

    @Override
    public Health health() {

        if(myEndpoint.getValue().equals("down")) {
            return Health.down().withDetail("reason", "죽었으").build();
        }
        return Health.up().build();
    }
}
