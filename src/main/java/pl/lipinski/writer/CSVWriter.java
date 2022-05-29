package pl.lipinski.writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CSVWriter implements SopraSteriaWriter {

    private static final Logger logger = Logger.getLogger(CSVWriter.class.getName());

    private static CSVWriter instance;

    private CSVWriter() {

    }

    public static CSVWriter getInstance() {
        if (instance == null) {
            instance = new CSVWriter();
        }
        return instance;
    }

    private List<String> formatData(Map<String, Long> data) {
        List<String> formattedData = new ArrayList<>();
        if(!data.containsKey("supply")) data.put("supply", 0L);
        if(!data.containsKey("buy")) data.put("buy", 0L);
        formattedData.add("supply," + data.get("supply"));
        formattedData.add("buy," + data.get("buy"));
        formattedData.add("result," + (data.get("supply") - data.get("buy")));
        return formattedData;
    }

    @Override
    public void write(String reportPath, Map<String, Long> data) {
        List<String> formattedData = formatData(data);
        File outputFile = new File(reportPath);
        try (PrintWriter printWriter = new PrintWriter(outputFile)) {
            formattedData.forEach(printWriter::println);
        } catch (FileNotFoundException e) {
            logger.info("File with given path: " + reportPath + " does not exist" +
                    "\nsee more: \n" + Arrays.toString(e.getStackTrace()));
        }
    }
}
