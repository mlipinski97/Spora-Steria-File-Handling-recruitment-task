package pl.lipinski;

import pl.lipinski.creator.CSVReportCreator;

public class Main {
    public static void main(String[] args) {
        CSVReportCreator.getInstance().createReport("/ItemX.csv", "./src/main/resources/reportItemX.csv");
        CSVReportCreator.getInstance().createReport("/ItemY.csv", "./src/main/resources/reportItemY.csv");
    }
}
