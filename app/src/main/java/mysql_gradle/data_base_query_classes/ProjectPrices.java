package mysql_gradle.data_base_query_classes;

public class ProjectPrices {
    private String projectId;
    private String price;

    public ProjectPrices() {
    }

    public ProjectPrices(String projectId, String price) {
        this.projectId = projectId;
        this.price = price;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectPrices that)) return false;

        if (getProjectId() != null ? !getProjectId().equals(that.getProjectId()) : that.getProjectId() != null)
            return false;
        return getPrice() != null ? getPrice().equals(that.getPrice()) : that.getPrice() == null;
    }

    @Override
    public int hashCode() {
        int result = getProjectId() != null ? getProjectId().hashCode() : 0;
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        return result;
    }
}
