package mysql_gradle.data_base_query_classes;

public class YoungestEldestWorkers {
    private String name;
    private String birthday;
    private String type;

    public YoungestEldestWorkers() {
    }

    public YoungestEldestWorkers(String name, String birthday, String type) {
        this.name = name;
        this.birthday = birthday;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof YoungestEldestWorkers that)) return false;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getBirthday() != null ? !getBirthday().equals(that.getBirthday()) : that.getBirthday() != null)
            return false;
        return getType() != null ? getType().equals(that.getType()) : that.getType() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getBirthday() != null ? getBirthday().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        return result;
    }
}
