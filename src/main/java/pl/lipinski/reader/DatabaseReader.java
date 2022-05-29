package pl.lipinski.reader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class DatabaseReader implements SopraSteriaReader {

    private static final Logger logger = Logger.getLogger(DatabaseReader.class.getName());

    private static DatabaseReader instance;

    public static DatabaseReader getInstance() {
        if (instance == null) {
            instance = new DatabaseReader();
        }
        return instance;
    }

    private DatabaseReader() {
    }

    @Override
    public Map<String, Long> read(String databasePath) {
        Map<String, Long> data = new HashMap<>();
        String query = "SELECT * FROM public.\"itemX\";";
        Statement queryStatement = null;
        try (Connection c = DriverManager.getConnection(databasePath,"postgres", "qwert")) {
            c.setAutoCommit(false);
            queryStatement = c.createStatement();
            ResultSet rs = queryStatement.executeQuery(query);
            while (rs.next()) {
                String operationName = rs.getString("operation");
                if (!data.containsKey(operationName)) {
                    data.put(operationName, rs.getLong("value"));
                } else {
                    data.put(operationName, data.get(operationName) + rs.getLong("value"));
                }
            }
            rs.close();
            queryStatement.close();
        } catch (Exception e) {
            logger.info("Problem occurred while trying to read from database" +
                    "\n read more: " +
                    "\n" + Arrays.toString(e.getStackTrace()));
        }
        return data;
    }
}
