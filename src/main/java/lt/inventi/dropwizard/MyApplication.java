package lt.inventi.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lt.inventi.dropwizard.healthchecks.DBHealthCheck;
import lt.inventi.dropwizard.representations.Employee;
import lt.inventi.dropwizard.resources.MyResource;
import lt.inventi.dropwizard.tasks.CleanDBTask;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyApplication extends Application<MyConfiguration> {

    public final Map<String, Employee> db = new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {
        new MyApplication().run(args);
    }

    @Override
    public String getName() {
        return "my-lovely-app";
    }

    public void run(MyConfiguration myConfiguration, Environment environment) throws Exception {
        environment.jersey().register(new MyResource(db));
        environment.healthChecks().register("database-healthcheck", new DBHealthCheck(db));
        environment.admin().addTask(new CleanDBTask("fire", db));
    }

    @Override
    public void initialize(Bootstrap<MyConfiguration> bootstrap) {
        super.initialize(bootstrap);
    }
}
