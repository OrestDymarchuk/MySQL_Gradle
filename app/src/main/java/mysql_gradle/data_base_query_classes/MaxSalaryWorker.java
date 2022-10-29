package mysql_gradle.data_base_query_classes;

public class MaxSalaryWorker {
    private String name;
    private String salary;

    public MaxSalaryWorker() {
    }

    public MaxSalaryWorker(String name, String salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getSalary() {
        return salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MaxSalaryWorker that)) return false;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return getSalary() != null ? getSalary().equals(that.getSalary()) : that.getSalary() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getSalary() != null ? getSalary().hashCode() : 0);
        return result;
    }
}
