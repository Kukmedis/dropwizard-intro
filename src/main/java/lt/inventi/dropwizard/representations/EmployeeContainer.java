package lt.inventi.dropwizard.representations;

import java.util.List;

public class EmployeeContainer {
    public final List<Employee> employees;

    public EmployeeContainer(List<Employee> employees) {
        this.employees = employees;
    }
}
