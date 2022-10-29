package mysql_gradle.preferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Preferences {
    public static final String DEFAULT_FILE_NAME = "prefs.json";
    public static final String DB_URL = "dbUrl";
    public static final String USER = "user";
    public static final String PASSWORD = "password";

    public static final String INIT_DB_SQL_PATH = "initDbSql";
    public static final String POPULATE_DB_SQL_PATH = "populateDbSql";

    public static final String MAX_SALARY_WORKER_DB_SQL_PATH = "maxSalaryWorkerDbSql";
    public static final String MAX_PROJECTS_CLIENT_DB_SQL_PATH = "maxProjectsClientDbSql";
    public static final String LONGEST_PROJECT_SQL_PATH = "longestProjectDbSql";
    public static final String YOUNGEST_ELDEST_WORKERS = "youngestEldestWorkersDbSql";
    public static final String PROJECT_PRICES = "projectPricesDbSql";

    private Map<String, Object> preferences = new HashMap<>();

    public Preferences(){
        this(DEFAULT_FILE_NAME);
    }

    public Preferences(String filename){
        try {
            String json = String.join(
                    "\n",
                    Files.readAllLines(Paths.get(filename))
            );
            TypeToken<?> typeToken = TypeToken.getParameterized(
                    Map.class,
                    String.class,
                    Object.class
            );
            preferences = new Gson().fromJson(json, typeToken.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getString(String key){
        return getPreferences(key).toString();
    }

    public Object getPreferences(String key){
        return preferences.get(key);
    }
}
