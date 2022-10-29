package mysql_gradle.data_base_query_classes;

import java.util.Objects;

public class MaxProjectsClient {
    private String name;
    private String projectCount;

    public MaxProjectsClient() {
    }

    public MaxProjectsClient(String name, String projectCount) {
        this.name = name;
        this.projectCount = projectCount;
    }

    public String getName() {
        return name;
    }

    public String getProjectCount() {
        return projectCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MaxProjectsClient that)) return false;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return getProjectCount() != null ? getProjectCount().equals(that.getProjectCount()) : that.getProjectCount() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getProjectCount() != null ? getProjectCount().hashCode() : 0);
        return result;
    }
}
