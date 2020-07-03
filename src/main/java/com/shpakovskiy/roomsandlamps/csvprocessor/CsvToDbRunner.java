package com.shpakovskiy.roomsandlamps.csvprocessor;

/**
 * Please, take a look at README file, located in package with this class.
 */

public class CsvToDbRunner {
    private static final String CSV_FILE_PATH = "CSV/File/Path";

    public static void main(String[] args) {
        CsvProcessor csvProcessor = new CsvProcessor(CSV_FILE_PATH);
        csvProcessor.convertCsvToDb();
        System.out.println("CONVERTED");
    }
}
