package pl.lipinski.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CSVReader implements SopraSteriaReader {

    private static final Logger logger = Logger.getLogger(CSVReader.class.getName());

    private static CSVReader instance;

    private CSVReader() {
    }

    public static CSVReader getInstance() {
        if (instance == null) {
            instance = new CSVReader();
        }
        return instance;
    }

    @Override
    public Map<String, Long> read(String filePath) {
        Map<String, Long> data = new HashMap<>();
        try {
            Path sourcePath = Paths.get(filePath);
            try (BufferedReader bufferedReader = Files.newBufferedReader(sourcePath)) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] values = line.split(",");
                    if (!data.containsKey(values[0])) {
                        data.put(values[0], Long.valueOf(values[1]));
                    } else {
                        data.put(values[0], data.get(values[0]) + Long.parseLong(values[1]));
                    }
                }
            }
        } catch (NullPointerException nullPointerException) {
            logger.info("No readable file at path: " + filePath);
        } catch (IOException ioException) {
            logger.info("Problem occurred while reading file: " + filePath +
                    "\nsee error message for more: \n" + Arrays.toString(ioException.getStackTrace()));
        }
        return data;
    }
}
