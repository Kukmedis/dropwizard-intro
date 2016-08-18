package lt.inventi.dropwizard.resources;

import lt.inventi.dropwizard.MyApplication;
import lt.inventi.dropwizard.representations.Employee;
import lt.inventi.dropwizard.representations.EmployeeContainer;
import lt.inventi.dropwizard.representations.EmployeeWithId;
import lt.inventi.dropwizard.representations.Id;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Resource
@Path("employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MyResource {

    private final Map<String, Employee> db;

    public MyResource(Map<String, Employee> db) {
        this.db = db;
    }

    @POST
    public Id createEmployee(Employee employee) {
        String id = UUID.randomUUID().toString();
        db.put(id, employee);
        return new Id(id);
    }

    @GET
    @Path("/{id}")
    public Employee getEmployee(@PathParam("id") String id) {
        Employee employee = db.get(id);
        if (employee == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return employee;
    }

    @GET
    public EmployeeContainer getEmployees() {
        return new EmployeeContainer(db.entrySet().stream().map(emp -> {
            EmployeeWithId employeeWithId = new EmployeeWithId();
            employeeWithId.id = emp.getKey();
            employeeWithId.firstName = emp.getValue().firstName;
            employeeWithId.lastName = emp.getValue().lastName;
            return employeeWithId;
        }).collect(Collectors.toList()));
    }

}
