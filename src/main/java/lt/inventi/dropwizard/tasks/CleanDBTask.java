package lt.inventi.dropwizard.tasks;

import com.google.common.collect.ImmutableMultimap;
import io.dropwizard.servlets.tasks.Task;
import lt.inventi.dropwizard.representations.Employee;

import java.io.PrintWriter;
import java.util.Map;

public class CleanDBTask extends Task {
    private final Map<String, Employee> db;

    public CleanDBTask(String name, Map<String, Employee> db) {
        super(name);
        this.db = db;
    }

    @Override
    public void execute(ImmutableMultimap<String, String> immutableMultimap, PrintWriter printWriter) throws Exception {
        db.clear();
    }
}
