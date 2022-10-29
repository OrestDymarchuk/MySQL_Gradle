package mysql_gradle.data_base_query_classes;

public class LongestProject {
    private String id;
    private String monthCount;

    public LongestProject() {
    }

    public LongestProject(String id, String monthCount) {
        this.id = id;
        this.monthCount = monthCount;
    }

    public String getId() {
        return id;
    }

    public String getMonthCount() {
        return monthCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LongestProject that)) return false;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        return getMonthCount() != null ? getMonthCount().equals(that.getMonthCount()) : that.getMonthCount() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getMonthCount() != null ? getMonthCount().hashCode() : 0);
        return result;
    }
}
