package org.spring.tutorial.examples.batch.utils;

import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CSVUtils {


    private static final Logger LOGGER = LoggerFactory.getLogger(CSVUtils.class);

    public static List<String[]> readCsvLines(Path file) throws IOException {

        try (
                Reader reader = Files.newBufferedReader(file);
                CSVReader csvReader = new CSVReader(reader, ';')// ; is our csv separator
        ) {

            List<String[]> records = csvReader.readAll();
            return records;
        }
    }
}
