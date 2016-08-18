package lt.inventi.dropwizard.healthchecks;

import com.codahale.metrics.health.HealthCheck;
import lt.inventi.dropwizard.representations.Employee;

import java.util.Map;

public class DBHealthCheck extends HealthCheck {

    private final Map<String, Employee> db;

    public DBHealthCheck(Map<String, Employee> db) {
        this.db = db;
    }

    @Override
    protected Result check() throws Exception {
        return db.isEmpty() ? Result.unhealthy("Can't work"): Result.healthy();
    }
}
